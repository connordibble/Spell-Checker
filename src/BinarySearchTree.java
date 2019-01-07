public class BinarySearchTree <E extends Comparable>{
    private TreeNode root;

    int numberNodes(){
        return numberNodes(this.root);
    }

    private int numberNodes(TreeNode node) {
        int count = 0;
        if (node != null) {
            count += numberNodes(node.left);
            count ++;
            count += numberNodes(node.right);
        }
        else{return 0;}

        return count;
    }

    int numberLeafNodes(){
        return numberLeafNodes(this.root);
    }

    private int numberLeafNodes(TreeNode node){
        if (node == null){return 0;}

        else if(node.left == null && node.right == null){return 1;}

        else{
            return numberLeafNodes(node.left) + numberLeafNodes(node.right);
        }
    }

    int height(){
        if (height(this.root) != 0){return height(this.root) - 1;}
        else{return height(this.root);}
    }

    private int height(TreeNode node){
        if (node == null || (this.root.right == null && this.root.left == null)){return 0;}


        else{
            int left = height(node.left);
            int right = height(node.right);

            if (left > right){return left + 1;}
            else{return right + 1;}
        }
    }

    public boolean search(E value) {
        boolean found = false;
        TreeNode node = root;

        while (!found && node != null) {
            if (node.value.equals(value)) {
                found = true;
            }
            else if (node.value.compareTo(value) < 0) {
                node = node.right;
            }
            else {
                node = node.left;
            }
        }

        return found;
    }

    public boolean insert(E value) {
        if (search(value)){return false;}
        else {
            if (root == null) {
                root = new TreeNode(value);
            } else {
                TreeNode parent = null;
                TreeNode node = root;

                while (node != null) {
                    parent = node;
                    if (node.value.compareTo(value) < 0) {
                        node = node.right;
                    } else {
                        node = node.left;
                    }
                }

                TreeNode newNode = new TreeNode(value);
                if (parent.value.compareTo(value) < 0) {
                    parent.right = newNode;
                } else {
                    parent.left = newNode;
                }
            }
            return true;
        }
    }

    public void display(String message) {
        System.out.print(message);
        display(this.root);
    }

    private void display(TreeNode node) {
        if (node != null) {
            display(node.left);
            System.out.println(node.value);
            display(node.right);
        }
    }

    public boolean remove(E value) {
        if (!search(value)){return false;}
        else {
            TreeNode parent = null;
            TreeNode node = root;
            boolean done = false;
            while (!done) {
                if (node.value.compareTo(value) < 0) {
                    parent = node;
                    node = node.right;
                } else if (node.value.compareTo(value) > 0) {
                    parent = node;
                    node = node.left;
                } else {
                    done = true;
                }
            }

            // If there is no left child this runs
            if (node.left == null) {
                if (parent == null) {
                    root = node.right;
                } else {
                    if (parent.value.compareTo(value) < 0) {
                        parent.right = node.right;
                    } else {
                        parent.left = node.right;
                    }
                }
            } else {
                TreeNode parentOfRight = node;
                TreeNode rightMost = node.left;
                while (rightMost.right != null) {
                    parentOfRight = rightMost;
                    rightMost = rightMost.right;
                }
                node.value = rightMost.value;
                if (parentOfRight.right == rightMost) {
                    parentOfRight.right = rightMost.left;
                } else {
                    parentOfRight.left = rightMost.left;
                }
            }
        }
        return true;
    }

    private class TreeNode {
        public E value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(E value) {
            this.value = value;
        }
    }
}
