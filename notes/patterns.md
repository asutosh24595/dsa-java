# DSA patterns

## Hashing
- Counting idiom: `map.put(x, map.getOrDefault(x, 0) + 1)`
- Complement lookup (Two Sum): key = what you search by, value = what you need back
- Grouping (Group Anagrams): build a key that's identical for all items in a group
- Value as index (Top K bucket sort): when values are bounded by n, use them as array indexes
- Return false on the first counterexample; return true only after the full loop