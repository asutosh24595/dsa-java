# Review: IntHashMap (AI-generated)

Code: correct. Good: bitmask indexing with power-of-2 capacity, hash spreading,
node relinking in resize, final key, boolean remove, MAX_CAPACITY guard.

## Findings (tests)
1. Tests use `assert`, which is disabled by default. Without -ea, every check is
   skipped and "All checks passed" prints even when get() is broken (verified).
   Fix: run with -ea or use if-checks that throw or JUnit.
2. Comment claimed that "collisions across resizes", but sequential keys never collide
   (each lands in its own bucket). The prev.next branch of remove() never runs
   (verified with a print). Fix: add keys that share a bucket (5, 21, 37 at cap 16).
3. (minor, verified) Notes correctly warn that -1 is ambiguous and recommend
   containsKey, but the test `assert map.get(-1) == -1` doesn't follow that advice:
   it still passes after remove(-1). Fix: assert containsKey(-1) && get(-1) == -1.