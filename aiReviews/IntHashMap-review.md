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
3. `assert map.get(-1) == -1` passes whether key -1 exists or not (-1 is also the
   missing-key value). Fix: assert containsKey(-1), or store a value other than -1.