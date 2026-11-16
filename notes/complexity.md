# Complexity

## Amortized O(1): ArrayList.add
A resize costs O(n), but doubling makes resizes rare, so n adds cost under 3n total,
which is amortized O(1) per add. Growing by +1 would be O(n) per add.
Java's ArrayList actually grows 1.5x; any constant factor > 1 gives amortized O(1).

## Nested loops: add or multiply? (aggregate analysis)
Mailroom: pigeonholes 6..1 hold only 3 letters in total.
Walking past every pigeonhole = 6 steps; picking up letters = 3 in total.
Total = 6 + 3 = 9, not 6 x 3 = 18: the letters are shared out and get used up.
- Top K bucket sort: buckets = pigeonholes (outer), unique numbers = letters (inner) -> O(n + u) = O(n)
- Encode/Decode: loop runs once per string (m), each round copies only its own word -> O(n + m)
- HashMap resize: outer visits each old bucket, inner moves each node once in total -> O(capacity + n)
- Multiply only when the inner work refills every outer iteration (e.g. `j < i` -> O(n^2))
- Test: does the inner work get used up (add) or refill (multiply)?

## Two kinds of work: O(n + m)
Packing boxes: n = items (characters), m = boxes (strings).
Each item is handled once (n); each box gets a label regardless of contents (m).
Don't drop m: 1,000 empty strings still cost 1,000 labels.

## Extra (auxiliary) space vs total space
Extra space excludes the output the problem forces you to build.
Product of Array Except Self: output O(n), extra O(1) (one running variable).
State both when it matters: "O(1) extra, output excluded".

## Hash map operations: O(1) average
indexFor jumps straight to a bucket; chains stay short (0-2 nodes) while load factor <= 0.75.
Worst case O(n) if every key collides (Java 8+ turns long chains into trees -> O(log n)).
Resizing keeps it O(1) amortized per put, same reasoning as ArrayList.

## Method
Cost each line, multiply by how often its loop runs, keep the largest term.
Always define variables (n, m, u, k).
Two separate (non-nested) loops add: O(n) + O(n) = O(n).