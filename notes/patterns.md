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