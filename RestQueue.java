public class RestQueue<T> {
    private Node<T> front;
    private Node<T> rear;

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public RestQueue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            return null; // Queue is empty
        }

        T data = front.data;
        front = front.next;

        if (front == null) {
            rear = null; // Last element dequeued, update rear
        }

        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void display() {
        Node<T> current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}


