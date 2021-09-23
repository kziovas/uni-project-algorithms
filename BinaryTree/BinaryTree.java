package binarytree;


public class BinaryTree{//the class that reprsenent the binary search tree

    private TreeNode root;
    
    //Binary search tree constructor
    public BinaryTree(){
        root=null;
    }
    
    //inserts a new node in the tree
    public void insert(int n){
        TreeNode newNode=new TreeNode(n);
        if(root==null){
            root=newNode;
        }
        else{
            TreeNode cur;
            cur=root;
            while(true){
                if(newNode.getKey()<cur.getKey() && cur.left!=null)//if the key of the new node is less than the current node move left
                    cur=cur.left;
                else if(newNode.getKey()<cur.getKey() && cur.left==null){//if the key of the new node is less than the current node and there is no left child, add the node to the left
                    cur.left=newNode;
                    newNode.parent=cur;
                    break;
                }
                else if(newNode.getKey()>=cur.getKey() && cur.right!=null)//if the key of the new node is greater than the current node move right
                    cur=cur.right;
                else if(newNode.getKey()>=cur.getKey() && cur.right==null){//if the key of the new node is greater than the current node and there is no right child, add the node to the right
                    cur.right=newNode;
                    newNode.parent=cur;
                    break;
                }                
            }
        }
    }
    
    //removes a node from the tree
    public void remove(int d){
        TreeNode cur;
        cur=root;
        
        while(cur!=null && cur.getKey()!=d){//search for the node
            if(d<cur.getKey())
                cur=cur.left;
            else
                cur=cur.right;
        }
        
        if(cur!=null){         
            if(cur.left==null){//If the node to be removed has no left child
                if(cur==root){
                    root=cur.right;
                    if(cur.right!=null){
                        cur.right.parent=null;
                    }
                }
                else if(cur==cur.parent.right){//if the node to be removed is right child
                    cur.parent.right=cur.right;
                    if(cur.right!=null)
                        cur.right.parent=cur.parent;
                }
                else{//if the node to be removed is left child
                    cur.parent.left=cur.right;
                    if(cur.right!=null)
                        cur.right.parent=cur.parent;
                }
            }//
            
            else if(cur.right==null){//only one child. Left child
                if(cur==root){
                    root=cur.left;
                    root.parent=null;
                }
                else if(cur==cur.parent.right){//if the node to be removed is right child
                    cur.parent.right=cur.left;
                    cur.left.parent=cur.parent;
                }
                else{//if the node to be removed is left child
                    cur.parent.left=cur.left;
                    cur.left.parent=cur.parent;                 
                }
            }//
            
            else{//if it has two children
                TreeNode minNode;
                
                //search for the next greater node (minNode)
                minNode=cur.right;//move right
                while(minNode.left!=null)//while it has left child move left
                     minNode=minNode.left;

                if(cur!=root){
                    if(minNode.parent==cur){//next greater node is cur right child
                        if(cur==cur.parent.right){//if node to be deleted is right child 
                            cur.parent.right=minNode;//make the next greater node the right child
                            minNode.parent=cur.parent;//update the parent node
                        }
                        else{//if node to be deleted is left child 
                            cur.parent.left=minNode;//make the next greater node the left child
                            minNode.parent=cur.parent;//update the parent node
                        }
                        minNode.left=cur.left;//set as left child of the new greater node the left child of the deleted node
                        cur.left.parent=minNode;//update the parent of the left child
                    }
                    else{//next greater node is cur right and then left...
                        if(cur==cur.parent.right)//substitute deleted node with minNode
                            cur.parent.right=minNode;
                        else
                            cur.parent.left=minNode;
                        
                        minNode.parent.left=minNode.right;//update parent 
                        if(minNode.right!=null)
                            minNode.right.parent=minNode.parent;
                        
                        minNode.left=cur.left;
                        minNode.right=cur.right;
                        
                        cur.left.parent=minNode;
                        cur.right.parent=minNode;

                        minNode.parent=cur.parent;
                    }
                }
                else{//if the node to be delete is the root (and has two children)
                    if(minNode.parent==cur){//next greater node is the right child
                        minNode.left=root.left;//set as left child the left child of the root
                        root.left.parent=minNode;
                        root=minNode;//update root
                        root.parent=null;//update root parent
                    }
                    else{//next greater node is cur right and then left...
                        minNode.parent.left=minNode.right;//make left child of parent of minNode the right child of minNode (substitutes minNide with its right child)
                        if(minNode.right!=null)
                            minNode.right.parent=minNode.parent;
                        
                        minNode.left=root.left;
                        root.left.parent=minNode;
                        
                        minNode.right=root.right;
                        root.right.parent=minNode;
                        root=minNode;//update the root pointer
                        root.parent=null;
                    }				
                }
            }//
		}	 
    }   
    
 
    public void inOrder(){
        System.out.println("=========InOrder Traversal=========");
        if(root!=null)
            root.inOrder();
    }
    
    public void preOrder(){
        System.out.println("=========preOrder Traversal=========");
        if(root!=null)
            root.preOrder();
    }    

    public void postOrder(){
        System.out.println("=========postOrder Traversal=========");
        if(root!=null)
            root.postOrder();
    }        
    
    public int treeHeight(){
        int r,l,max;
        
        if(root==null)
            return 0;
        else
            return root.height();
    }
    
    public static void main(String[] args) {
        BinaryTree tree=new BinaryTree();
        
        tree.insert(50);
        tree.insert(60);
        tree.insert(55);
        tree.insert(53);
        tree.insert(54);
        tree.insert(20);

       
        tree.inOrder();
        tree.remove(50);

        tree.inOrder();
        tree.preOrder();
        
        System.out.println("Tree Height:" + tree.treeHeight());
    }
    
}
