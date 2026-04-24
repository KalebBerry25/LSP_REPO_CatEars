Part 1:

Shared Resource #1:

    nextId (shared counter across threads)

Shared Resource #2:

    requests list (shared mutable collection)

Concurrency Problem:

    Race condition — multiple threads can access and modify shared resources at the same time, causing duplicate IDs or inconsistent request storage.

Why addRequest() is unsafe:

    addRequest() calls getNextId() and then writes to requests without synchronization.
    Two threads may:
        Read the same nextId before it increments leading to duplicate IDs
        Modify requests simultaneously creating potential data corruption or lost updates

Part 2:

Fix A: Explanation

    Not correct
    Synchronizing getNextId() ensures unique IDs, but:
    requests.add() is still not protected
    The full operation is not atomic
    Race condition still exists on the list

Fix B: Explanation

    Correct
    Synchronizing addRequest() ensures:
    Only one thread executes the entire method at a time
    Both ID generation and list insertion are atomic
    Prevents duplicate IDs and concurrent modification issues

Fix C: Explanation

    Not correct
    Synchronizing getRequests() only protects reading the list
    Does NOT prevent:
    Race conditions in addRequest()
    Concurrent writes to the list
    Core issue remains unresolved


Part 3:

    No, getNextId() should not be public.
    According to Arthur Riel’s heuristics, classes should hide internal implementation details.
    nextId is an internal mechanism, not part of the class’s responsibility exposed to users.
    Making it public:
        Breaks encapsulation
        Allows misuse (external callers generating IDs incorrectly)
        It should be private or at most protected and only used internally.


Part 4:

Description:

    Use AtomicInteger from java.util.concurrent.atomic instead of synchronized.
    This provides thread-safe, lock-free increment operations.
    Ensures unique IDs without explicit synchronization.
    Combine with a thread-safe collection for full safety.

Code Snippet:

    import java.util.concurrent.atomic.AtomicInteger;
    import java.util.concurrent.CopyOnWriteArrayList;
    import java.util.List;

    public class RequestManager {
        private AtomicInteger nextId = new AtomicInteger(1);
        private List<String> requests = new CopyOnWriteArrayList<>();

        public void addRequest(String studentName) {
            int id = nextId.getAndIncrement();
            String request = "Request-" + id + " from " + studentName;
            requests.add(request);
        }
    }