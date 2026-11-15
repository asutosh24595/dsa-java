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
- Multiply only when the inner work refills every outer iteration (e.g. `j < i` -> O(n^2))
- Test: does the inner work get used up (add) or refill (multiply)?

## Method
Cost each line, multiply by how often its loop runs, keep the largest term.
Always define variables (n, m, u, k).