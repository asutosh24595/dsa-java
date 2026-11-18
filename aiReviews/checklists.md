# AI code review checklist

Write findings BEFORE asking anyone. For each finding: what, why it matters,
an input or experiment that proves it and the fix.

## 1. Claims
- [ ] Is every claim in comments/notes true? ("handles negatives", "O(1)",
      "covers collisions", "all checks passed") - verify each one, don't trust it
- [ ] Does it do what was asked (signatures, return values, constraints)?

## 2. Tests
- [ ] Do the tests actually run? (assert needs -ea; are they wired into anything?)
- [ ] Do they FAIL when the code is broken? Break something on purpose and check
- [ ] Is every branch exercised? (each if/else, loop that runs 0 times, early return)
      Probe: put a print in the branch and see if it ever runs
- [ ] Can each check tell success from failure? (would it pass if the result were wrong?
      e.g. get(-1) == -1 when -1 is also the "missing" value)
- [ ] Do test inputs hide bugs? (first element 1, sequential keys, already-sorted data)

## 3. Edge cases
- [ ] Empty input, single element, all duplicates
- [ ] Negatives, zero, Integer.MIN_VALUE / MAX_VALUE (overflow, % with negatives)
- [ ] Collisions / ties / repeated keys
- [ ] Full array (no spare capacity hiding an out-of-bounds read)
- [ ] Lengths >= 10 when parsing numbers

## 4. Logic traps I've hit myself
- [ ] Every walking loop advances (node = node.next, i++)
- [ ] Order of two lines: check-then-put, store-then-update, prev = node then node = node.next
- [ ] Loop bounds: last valid index + 1; substring end excluded; position = start + count
- [ ] Return values used (x = sort(x)); in-place methods not assigned (list.sort() is void)
- [ ] best = Math.max(best, candidate), not best = candidate
- [ ] Rehash/recompute anything that depends on a size that changed

## 5. Complexity
- [ ] Is the stated time/space correct? Cost each line, multiply by loop counts
- [ ] Nested loops: does inner work refill (multiply) or get used up (add)?
- [ ] Hidden costs: toCharArray, substring, copyOfRange, string += in loops

## 6. Code quality
- [ ] Names say what things hold (seen, count, not c, temp, group)
- [ ] No duplicated logic (extract a helper)
- [ ] Encapsulation: helpers private, internals not exposed
- [ ] No debug prints, no commented-out code, no redundant `? true : false`