import java.util.ArrayList;
import java.util.List;

public class SeparateChaining<K, V> {
    private List<HashNode<K, V>> buckets;
    private int capacity;
    private int countOfItems;

    public SeparateChaining() {
        capacity = 2;
        resize(capacity);
    }

    private void resize(int capacity) {
        if (buckets == null) {
            buckets = new ArrayList<>();
        }

        buckets = new ArrayList<>();

        while (buckets.size() < capacity) {
            buckets.add(null);
        }

        this.capacity = capacity;
    }

    public int size() {
        return capacity;
    }

    public boolean isEmpty() {
        for (int i = 0; i < capacity; i++) {
            if (buckets.get(i) != null) {
                return false;
            }
        }
        return true;
    }

    public int hash(K key) {
        return key.hashCode();
    }

    private int getBucketIndex (K key) {
        return hash(key) % capacity;
    }

    public void delete (K key) {
        if (!contains(key)) {
            System.out.printf("FAILED TO DELETE %s%n", key);
            return;
        }
        int index = getBucketIndex(key);

        HashNode<K, V> node = buckets.get(index);
        if (node.key == key) {
            buckets.set(index, node.next);
        } else {
            do {
                if (node.next.key == key) {
                    if (node.next.next != null) {
                        node.next = node.next.next;
                    } else {
                        node.next = null;
                    }
                    break;
                }
                node = node.next;
            } while (node.next != null);
        }
        System.out.println("DELETED");
        countOfItems--;
    }

    public V get (K key) {
        int index = getBucketIndex(key);
        return null;
    }

    public void put (K key, V value) {
        if (checkForRehash()) {
            rehash();
        }

        int index = getBucketIndex(key);

        HashNode<K, V> newNode = new HashNode<>(key, value, hash(key));

        HashNode<K, V> currentNode = buckets.get(index);

        if (currentNode == null) {
            buckets.set(index, newNode);
        } else {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        countOfItems++;
        System.out.println("PUT");
    }

    private void rehash() {
        List<HashNode<K, V>> buckets = this.buckets;
        resize(countOfItems);
        for (HashNode<K, V> bucket :
                buckets) {
            HashNode<K, V> tempNode = bucket;
            while (tempNode != null) {
                put(tempNode.key, tempNode.value);
                tempNode = tempNode.next;
            }
        }
        System.out.println("REHASHED");
    }

    private boolean checkForRehash() {
        return countOfItems / 2 >= capacity;
    }

    public boolean contains (K key) {
        int index = getBucketIndex(key);
        HashNode<K, V> node = buckets.get(index);
        while (node != null) {
            if (node.key == key) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void print() {
        for (HashNode<K, V> node :
                buckets) {
            if (node != null) {
                StringBuilder str = new StringBuilder(node + "->");
                HashNode<K, V> tempNode = node;
                while (tempNode != null) {
                    str.append(tempNode).append("->");
                    tempNode = tempNode.next;
                }
                str.append("null");
                System.out.println(str);
            }
            else {
                System.out.println(node);
            }
        }
    }
}
