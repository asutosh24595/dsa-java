# Java gotchas (JS habits that break in Java)

| JS habit | Java |
|---|---|
| `[i, j]` array literal | `new int[]{i, j}` |
| `[]` empty list | `new ArrayList<>()` |
| `map.has(k)` | `map.containsKey(k)` |
| `if (!map.get(k))` (truthy) | no truthy values: use `containsKey` or `== null` |
| `str[i]` | `str.charAt(i)` |
| print an array directly | `Arrays.toString(arr)` |
| `list.sort()` returns the list | `sort` returns void: don't assign or chain it |
| `set.sort()` | sets can't be sorted: copy to a List first |
| `toArray()` gives int[] | gives Object[]; use `mapToInt(Integer::intValue).toArray()` |

Other:
- `javac` compiles to .class bytecode; `java` runs the .class. Recompile after every change.
- A class in a package runs from the root: `java datastructures.MyArrayList`
- Loop bounds: find the last valid index, then `i < last + 1`.