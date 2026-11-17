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

## Map write methods: put vs putIfAbsent vs computeIfAbsent

| Method | Key missing | Key exists |
|---|---|---|
| `put(k, v)` | adds | overwrites |
| `putIfAbsent(k, v)` | adds | keeps old value |
| `computeIfAbsent(k, f)` | adds f's result | keeps old value; f never runs |

Example:
    Map<Integer, Integer> m = new HashMap<>();
    m.put(3, 0);            // {3=0}
    m.put(3, 1);            // {3=1}  overwritten
    m.putIfAbsent(3, 9);    // {3=1}  unchanged, key exists
    m.putIfAbsent(4, 9);    // {3=1, 4=9}  added, key was missing

putIfAbsent vs computeIfAbsent (the real difference):
    map.putIfAbsent(key, new ArrayList<>());
    // new ArrayList is created EVERY time, even if the key exists (then thrown away)

    map.computeIfAbsent(key, k -> new ArrayList<>());
    // new ArrayList is created ONLY if the key is missing

Grouping idiom (one line, returns the list so you can add to it):
    map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);

## Arrays of generic types
    new ArrayList<>[n]         // does not compile: can't create generic arrays with <>
    new List[n]                // works (raw type warning is fine)
Creating the array makes empty slots (null), not lists. Either pre-fill every slot
with new ArrayList<>() or create lazily with a null check.

## getOrDefault only reads
    map.getOrDefault(k, 0)     // returns 0 if missing, but stores nothing
    map.put(k, map.getOrDefault(k, 0) + 1)   // put does the storing