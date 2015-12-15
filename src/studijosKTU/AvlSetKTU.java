package studijosKTU;

import java.util.Comparator;

/**
 * Rikiuojamos objektų kolekcijos - aibės realizacija AVL-medžiu.
 *
 * @param <E> Aibės elemento tipas. Turi tenkinti interfeisą Comparable<T>, arba per
 * klasės konstruktorių turi būti paduodamas Comparator<T> klasės objektas.
 * @užduotis Peržiūrėkite ir išsiaiškinkite pateiktus metodus.
 *
 * @author darius.matulis@ktu.lt
 */
public class AvlSetKTU<E extends Comparable<E>> extends BstSetKTU<E>
        implements SortedSetADT<E> {

    public AvlSetKTU() {
    }

    public AvlSetKTU(Comparator<? super E> c) {
        super(c);
    }

    /**
     * Aibė papildoma nauju elementu.
     *
     * @param element
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in add(E element)");
        }
        root = addRecursive(element, (AVLNode<E>) root);

    }


    /**
     * Privatus rekursinis metodas naudojamas add metode;
     *
     * @param element
     * @param node
     * @return
     */
    private AVLNode<E> addRecursive(E element, AVLNode<E> node) {
        if (node == null) {
            size++;
            return new AVLNode<>(element);
        }
        int cmp = c.compare(element, node.element);

        if (cmp < 0) {
            node.setLeft(addRecursive(element, node.getLeft()));
            if ((height(node.getLeft()) - height(node.getRight())) == 2) {
                int cmp2 = c.compare(element, node.left.element);
                node = (cmp2 < 0) ? rightRotation(node) : doubleRightRotation(node);
            }
        } else if (cmp >= 0) {
            node.right = addRecursive(element, node.getRight());
            if ((height(node.getRight()) - height(node.getLeft())) == 2) {
                int cmp2 = c.compare(node.right.element, element);
                node = (cmp2 < 0) ? leftRotation(node) : doubleLeftRotation(node);
            }
        }
        if(node != null)
            node.height = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
        return node;
    }

    @Override
    public void remove(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in remove(E element)");
        }
        if(size <= 0){
            throw new IllegalArgumentException("Set is empty");
        }
        root = removeRecursive(element, (AVLNode<E>) root);
    }

    private AVLNode<E> removeRecursive(E element, AVLNode<E> node) {
        if (node == null) {
            return null;
        }
        // Medyje ieškomas šalinamas elemento mazgas;
        int cmp = c.compare(element, node.element);

        if (cmp < 0) {
            node.setLeft(removeRecursive(element, node.getLeft()));
            if(height(node.getRight()) - height(node.getLeft()) == 2){
                if(height(node.getRight().getLeft()) > height(node.getRight().getRight())){
                    node = doubleLeftRotation(node);
                }else{
                    node = leftRotation(node);
                }
            }
        } else if (cmp > 0) {
            node.setRight(removeRecursive(element, node.getRight()));
            if(height(node.getLeft()) - height(node.getRight()) == 2){
                if(height(node.getLeft().getLeft()) > height(node.getLeft().getRight())){
                    node = rightRotation(node);
                }else{
                    node = doubleRightRotation(node);
                }
            }
        } else if (node.getLeft() != null && node.getRight() != null) {
            node.element = ((AVLNode<E>) getMax(node.getLeft())).element;
            node.setLeft(removeRecursive(node.element, node.getLeft()));
            if(height(node.getRight()) - height(node.getLeft()) == 2){
                if(height(node.getRight().getLeft()) > height(node.getRight().getRight())){
                    node = doubleLeftRotation(node);
                }else{
                    node = leftRotation(node);
                }
            }
        } else {// Kiti atvejai
            node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
            size--;
        }
        if(node != null){
            node.height = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
        }
        return node;
    }

    private AVLNode<E> removeMax(AVLNode<E> node) {

        if (node == null) {
            return null;
        } else if (node.getRight() != null) {
            node.setRight(removeMax(node.getRight()));
            if ((height(node.getRight()) - height(node.getLeft())) == 2) {
                int cmp2 = c.compare(node.right.element, node.element);
                node = (cmp2 < 0) ? leftRotation(node) : doubleLeftRotation(node);
            }
            return node;
        } else {
            return node.getLeft();
        }
    }

    //==============================================================================
// Papildomi privatūs metodai, naudojami operacijų su aibe realizacijai
// AVL-medžiu;
//==============================================================================
//==============================================================================
//         n2
//        /                n1
//       n1      ==>      /  \
//      /                n3  n2
//     n3
//==============================================================================
    private AVLNode<E> rightRotation(AVLNode<E> n2) {
        AVLNode<E> n1 = n2.getLeft();
        if(n1 == null)
            return null;
        n2.setLeft((AVLNode<E>) n1.right);
        n1.setRight(n2);
        n2.height = Math.max(height(n2.getLeft()), height(n2.getRight())) + 1;
        n1.height = Math.max(height(n1.getLeft()), height(n2)) + 1;
        return n1;
    }

    private AVLNode<E> leftRotation(AVLNode<E> n1) {
        AVLNode<E> n2 = n1.getRight();
        if(n2 == null)
            return null;
        n1.setRight((AVLNode<E>) n2.left);
        n2.setLeft(n1);
        n1.height = Math.max(height(n1.getLeft()), height(n1.getRight())) + 1;
        n2.height = Math.max(height(n2.getRight()), height(n1)) + 1;
        return n2;
    }

//==============================================================================
//        n3               n3
//       /                /                n2
//      n1      ==>      n2      ==>      /  \
//       \              /                n1  n3
//        n2           n1
//==============================================================================     
    private AVLNode<E> doubleRightRotation(AVLNode<E> n3) {
        n3.left = leftRotation(n3.getLeft());
        return rightRotation(n3);
    }

    private AVLNode<E> doubleLeftRotation(AVLNode<E> n1) {
        n1.right = rightRotation(n1.getRight());
        return leftRotation(n1);
    }

    private int height(AVLNode<E> n) {
        return (n == null) ? -1 : n.height;
    }

    /**
     * Vidinė kolekcijos mazgo klasė
     *
     * @param <N> mazgo elemento duomenų tipas
     */
    protected class AVLNode<N> extends BstNode<N> {

        protected int height;

        protected AVLNode(N element) {
            super(element);
            this.height = 0;
        }

        protected void setLeft(AVLNode<N> left) {
            this.left = (BstNode<N>) left;
        }

        protected AVLNode<N> getLeft() {
            return (AVLNode<N>) left;
        }

        protected void setRight(AVLNode<N> right) {
            this.right = (BstNode<N>) right;
        }

        protected AVLNode<N> getRight() {
            return (AVLNode<N>) right;
        }
    }
}
