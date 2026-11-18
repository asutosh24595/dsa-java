# Operating systems (OSTEP)

## Ch 2: Introduction, the three big ideas
- Virtualization: the OS turns one physical resource (CPU, memory) into the illusion
  of many private ones, one per program.
- Concurrency: the problems that arise when several things run at once and share data
  (two threads incrementing one counter -> wrong total).
- Persistence: storing data on disk so it survives crashes and power loss.

## Ch 4: The process
- Process = a running instance of a program. Program = code on disk (the recipe);
  process = the program actually executing (someone cooking it right now).
  Launching the same program twice = two processes.
- Why it matters: the OS virtualizes the CPU by time sharing - running one process,
  stopping it, running another - so each seems to have its own CPU.

### States and transitions
- Running: executing on the CPU
- Ready: could run, waiting for the OS to pick it
- Blocked: waiting for something (e.g. disk I/O), can't run until it finishes

| From -> To | Why |
|---|---|
| Ready -> Running | scheduled by the OS |
| Running -> Ready | descheduled (OS switches to another process, e.g. timer) |
| Running -> Blocked | process starts I/O and must wait |
| Blocked -> Ready | the I/O completes |

Also: an initial state while being created, and a final ("zombie") state after exit,
until the parent collects its exit status.

### What the OS tracks: Process Control Block (PCB), one per process
- PID (process ID) and parent
- state (ready / running / blocked)
- register context: program counter, stack pointer, etc. - saved on a switch,
  restored when it runs again (that save/restore is a context switch)
- memory: its address space
- open files

### Mechanism vs policy
- Mechanism = how to do it (context switch)
- Policy = which process to run next (scheduler)

## Ch 5: Process API (fork, exec, wait)
- fork(): creates a near-identical copy of the calling process (the child),
  continuing from the same point. Returns twice: 0 in the child, the child's PID
  in the parent (-1 on failure). Parent and child then run independently;
  which runs first is up to the scheduler.
- exec(): replaces the current process's program with a new one. Same process,
  same PID, no new process. On success it never returns (the old code is gone).
- wait(): parent blocks until a child finishes, and collects its exit status
  (this is what clears the zombie state from ch 4).

### Why fork and exec are separate
The gap between them lets the shell set things up for the new program.
Example: `ls > out.txt` - the shell forks, the child redirects its output to
out.txt, then execs ls. ls writes "normally" but its output goes to the file.

### The shell in one loop
read command -> fork() -> child: exec(command) -> parent: wait() -> repeat

## Ch 6: Limited Direct Execution
Goal: run programs directly on the CPU (fast) while the OS keeps control.
- User mode (restricted) vs kernel mode (full access)
- System call = trap into the kernel via a pre-set trap table; return-from-trap back to user mode
- Timer interrupt: hardware regains control every few ms, so no program can hog the CPU
- Context switch: save current registers (PC, SP...) to the kernel stack, restore the next process's

SDE takeaways:
- syscalls (I/O, printing) are expensive -> buffer and batch
- context switches cost time -> thread pools, event loops
- user/kernel separation = isolation/security
- code can be preempted between ANY two instructions -> root of race conditions

Summary from Chapter 2 - 6:

**1. Why the OS exists (ch 2)**
The OS takes one computer and makes it feel like many: every program thinks it has its **own CPU** and **own memory** (virtualization). It also handles many things happening at once (concurrency) and keeps data safe on disk (persistence).

**2. What a process is (ch 4)**
A **program** is code sitting on disk. A **process** is that program **actually running**, with its own memory, its own place in the code, and its own open files. Run the same program twice and you get two processes.

**3. Birth: how the process is created (ch 5)**
- The shell (itself a process) calls **`fork()`**, which creates an almost exact copy of the shell: the child.
- The child calls **`exec("ls")`**, which throws away the shell code inside it and loads `ls` instead. Same process, new program.
- The OS sets up its **registers**: the **program counter** points at the start of `ls`, and the **stack pointer** points at a fresh stack.
- The OS records everything about it in a **PCB** (Process Control Block): its ID, parent, state, saved registers, memory and open files.

**4. Life: running and waiting (ch 4 and 6)**
The process moves between three states:
- **Ready:** it could run, and it's waiting its turn
- **Running:** it's on the CPU right now
- **Blocked:** it's waiting for something slow, like the disk

How it runs (ch 6): **directly on the CPU** for speed, but in **user mode**, which is restricted.
- When `ls` needs to read the directory from disk, it makes a **system call**. A **trap** switches into **kernel mode**, the kernel does the disk work, and **return-from-trap** goes back to user mode. While it waits for the disk, it's **blocked**.
- A **timer interrupt** fires every few milliseconds, giving the OS control back. The OS may pause `ls` (back to **ready**) and run something else with a **context switch**: save `ls`'s registers into its PCB, load another process's registers. Later, it restores `ls`'s registers and it continues exactly where it stopped, never knowing it was paused.

**5. Death: exit and cleanup (ch 4 and 5)**
- `ls` finishes and exits. It becomes a **zombie**: it's done, but its exit status is kept until the parent collects it.
- The shell was waiting in **`wait()`**. It collects the exit status, the zombie is removed, and the shell prints the prompt again.

**The whole lifecycle in one line:**
> fork (copy) → exec (load new program) → ready ⇄ running ⇄ blocked (system calls, timer interrupts, context switches) → exit → zombie → parent's wait() cleans it up

**The five ideas to remember as an SDE:**
1. **Process = a running program** with its own isolated memory, so one crashing app doesn't take down others.
2. **fork + exec + wait** is how every shell, and every server that spawns workers, creates processes.
3. **System calls cross into the kernel and are slow,** so batch and buffer I/O.
4. **Context switches cost time,** which is why thread pools and event loops exist.
5. **Your code can be paused between any two instructions,** which is the root of every concurrency bug you'll meet.

