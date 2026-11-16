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