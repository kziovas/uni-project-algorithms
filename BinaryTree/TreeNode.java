package binarytree;

public class TreeNode {//the class that represents the node of the binary tree
    protected TreeNode left;//pointer to left child
    protected TreeNode right;//pointer to right child
    protected TreeNode parent;//pointer to parent node
    private int key;
    
    //constructor
    public TreeNode(){
        left=null;
        right=null;
        parent=null;
    }
    //constructor
    public TreeNode(int d){
        left=null;
        right=null;
        parent=null;
        key=d;
    }
    
    //returns the key of the node
    public int getKey(){
        return key;
    }

    //Inorder traversal of the tree
    public void inOrder(){
        if(this.left!=null)
            left.inOrder();
        System.out.println(this.key);
        if(this.right!=null)
            right.inOrder();
    }
    
    //Preorder traversal of the tree
    public void preOrder(){
        System.out.println(this.key); 
        if(this.left!=null)
            left.preOrder();
        if(this.right!=null)
            right.preOrder();
    }    
    
    //Postorder traversal of the tree
    public void postOrder(){ 
        if(this.left!=null)
            left.preOrder();
        if(this.right!=null)
            right.preOrder();
        System.out.println(this.key);
    }
    
    //recursive method that returns the height of the tree
    public int height(){
        int l=0,r=0;
        
        if(this.left==null && this.right==null)
            return 0;
        if(this.left!=null)
            l=left.height();
        if(this.right!=null)
            r=right.height();
        return l>r?l+1:r+1;
              
    }
}
