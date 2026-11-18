# DSA patterns

## Hashing
- Counting idiom: `map.put(x, map.getOrDefault(x, 0) + 1)`
- Complement lookup (Two Sum): key = what you search by, value = what you need back
- Grouping (Group Anagrams): build a key that's identical for all items in a group
  (sorted string, or count array via Arrays.toString)
- Value as index (Top K bucket sort): when values are bounded by n, use them as array indexes
- Return false on the first counterexample; return true only after the full loop

## Strings
- Length-prefix encoding (Encode/Decode): write "length#content" for each string.
  Decoder searches for '#' only in the number area, then counts through the word.
  Safe for any content, because the length says where the word ends, not a delimiter.
- substring(start, end): start included, end excluded; end - start = character count
- position = start + count (don't mix counts and positions)

## Prefix / suffix
- Product of Array Except Self: answer[i] = (everything left of i) x (everything right of i)
- Pass 1 left to right builds the prefix; pass 2 right to left with a running variable
- Running products start at 1 (empty product); running sums start at 0
- Coming up: prefix sums + hashmap (Subarray Sum Equals K)

## Ordering rules (when an element must not count itself)
- Two Sum: check the map, then put
- Product Except Self: store result, then update the running product
- Linked list remove: prev = node, then node = node.next

## Testing my own code
- Pick inputs that would expose bugs: no 1s at the start, distinct values
- Then edge cases: empty, single element, zeros, negatives, duplicates
- Force collisions on purpose (keys 5, 21, 37 with 16 buckets)
- Lengths >= 10 for anything that parses numbers

## Hashing (more)
- Many groups at once (Valid Sudoku): one set per group in arrays of sets;
  !set.add(x) on any group = duplicate
- 2D cell -> box index: (row / 3) * 3 + (col / 3)  (same idea as row * width + col)
- Run starts (Longest Consecutive): x is a start if x - 1 is NOT in the set;
  count up only from starts -> O(n). Without the check it's O(n^2)
- Best so far: best = Math.max(best, candidate), never best = candidate

## Divide and conquer: merge sort
- Split in half until single elements (already sorted), merge back up
- sort() only splits; merge() does all the comparing, and only works on sorted inputs
- Merge = two pointers: take the smaller front, advance that pointer, copy leftovers
- Use <= in merge to keep equal elements in order (stable)