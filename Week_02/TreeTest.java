package Tree;

import Tree.Bean.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author jiangshine
 * @version 1.0
 * @date 2021/2/1 17:07
 * @Content
 */
/*
比对了下二叉树的前中后序遍历的3种方法，递归，迭代，Morris
递归，迭代的时间和空间复杂度是O（n）,Morris时间是O（n），空间是O（1）
因此，如果记得Morris算法，就使用Morris ，否则使用递归，递归的代码比迭代好记好写

 */
public class TreeTest {
    List<Integer> res = new ArrayList<Integer>();

    @Test
    public void test() {
//        TreeNode testNode = createTree();//[1,2,3,4,5]依次节点
        //94二叉树的中序遍历
//        inorderTraversal(testNode);  //颜色标记法
//        System.out.println(res);
        //144. 二叉树的前序遍历
//        preorderTraversal(testNode);
//        System.out.println(res);
        //145. 二叉树的后序遍历
//        postorderTraversal(testNode);
//        System.out.println(res);

        //429. N 叉树的层序遍历
//        Integer[] nums = {1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14};
        Integer[] nums = {1, null, 3, 2, 4, null, 5, 6};
        TreeNode root = createNTree(nums);
//        System.out.println(levelOrder(root));
        //589. N叉树的前序遍历
        preorder(root);
       // 590. N叉树的后序遍历
//        postorder(root);

    }

    public List<Integer> postorder(TreeNode root) {
//        List<Integer> postorders = new ArrayList<>();
//        Npostorder(root,postorders);
//        return postorders;
        /*
        方法二：迭代
时间复杂度：时间复杂度：O(M)，其中 M 是 N 叉树中的节点个数。每个节点只会入栈和出栈各一次。
空间复杂度：O(M)。在最坏的情况下，这棵 N 叉树只有 2 层，除根节点的所有节点即第 2 层的节点都是根节点的孩子。将根节点推出栈后，需要将这些节点都放入栈，共有M−1 个节点，因此栈的大小为 O(M)。
         */
        //重点，这种使用2个LinkedList来作为结果保存和转换的，很巧妙，可以充分利用LinkedList可以作为双端队列的特性，可以1个或2个作为栈，一个或2个作为队列
        LinkedList<TreeNode> stack = new LinkedList<>();//此时作为栈使用，先入后出
        LinkedList<Integer> stacklist = new LinkedList<>();//此时也作为栈使用，先入后出
        if (root == null) return stacklist;
        stack.offer(root);//添加到链表尾部
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();//删除链表尾部元素
            stacklist.addFirst(node.val);//添加到返回列表的头部
            for (TreeNode child : node.children) {
                stack.offer(child);
            }
        }
        return stacklist;
    }

    private void Npostorder(TreeNode root, List<Integer> postorders) {
         /*
        方法一：递归
        时间：O（n）
        空间：O（n）
         */
//        if (root == null) {
//            return;
//        }
//        for (TreeNode node : root.children) {
//            Npostorder(node,postorders);
//        }
//        postorders.add(root.val);
    }

    public List<Integer> preorder(TreeNode root) {
        /*
        方法一：递归
        时间：O（n）
        空间：O（n）
         */
        List<Integer> preorders = new ArrayList<>();
        Npreorder(root,preorders);
        return preorders;
    }

    public void Npreorder(TreeNode root,List<Integer> preorders) {
//        if (root == null) {
//            return;
//        }
//        preorders.add(root.val);
//        for (TreeNode node : root.children) {
//            Npreorder(node,preorders);
//        }
          /*
        方法二：迭代
时间复杂度：时间复杂度：O(M)，其中 M 是 N 叉树中的节点个数。每个节点只会入栈和出栈各一次。
空间复杂度：O(M)。在最坏的情况下，这棵 N 叉树只有 2 层，除根节点的所有节点即第 2 层的节点都是根节点的孩子。将根节点推出栈后，需要将这些节点都放入栈，共有 M−1 个节点，因此栈的大小为O(M)。
         */
        LinkedList<TreeNode> stack = new LinkedList<>();
        if (root == null) return;
        stack.offer(root);//在栈尾添加
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();//删除栈尾元素
            preorders.add(node.val);
            Collections.reverse(node.children);
            for (TreeNode child : node.children) {
                stack.offer(child);
            }
        }
        //todo  有空的时候或者复习的时候将Morris解法也写上
    }

    //初始化一颗二叉树，用于测试用
    @Test
    public TreeNode createTree() {
        int data[] = {1, 2, 3, 4, 5};
        List<TreeNode> list = new ArrayList<TreeNode>();
        for (int tempdata : data) {
            list.add(new TreeNode(tempdata));
        }
        TreeNode treeNode = new TreeNode();
        treeNode.root = list.get(0);//将第一个元素设置为根节点
        for (int i = 0; i < list.size() / 2; i++) {
            if ((i * 2 + 1) < list.size()) {
                list.get(i).setLeft(list.get(i * 2 + 1));
            }
            if ((i * 2 + 2) < list.size()) {
                list.get(i).setRight(list.get(i * 2 + 2));
            }
        }
        return treeNode.root;
    }

    //初始化N叉树
    public TreeNode createNTree(Integer[] nums) {
        Queue<TreeNode> all = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(nums[0]);
        all.add(root);
        List<TreeNode> children = new ArrayList<TreeNode>();
        TreeNode parent = null;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == null) {
                parent = all.remove();//移除头结点
                continue;
            }
            children = new ArrayList<TreeNode>();
            while (i < nums.length && nums[i] != null) {
                TreeNode node = new TreeNode(nums[i++]);
                all.add(node);
                children.add(node);
            }
            i--;
            parent.setChildren(children);
        }
        return root;
    }

    //429. N 叉树的层序遍历
    List<List<Integer>> results = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        /*
        这个就用方法一的广度优先搜索就行
         */
        /*
        方法一：利用队列实现广度优先搜索
        时间复杂度：O(n)。n 指的是节点的数量。
        空间复杂度：O(n)。
         */
//        if (root == null) return results;
//        Queue<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(root);
//        List<Integer> result = null;
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            result = new ArrayList<Integer>();
//            for (int i = 0; i < size; i++) {
//                TreeNode node = queue.poll();
//                result.add(node.val);
//                queue.addAll(node.children);
//            }
//            results.add(result);
//        }
//        return results;
        /*
        方法二：简化的广度优先搜索
时间复杂度：O(n)。n 指的是节点的数量。
空间复杂度：O(n)，我们的列表包含所有节点。
         */
//        if (root == null) return results;
//        List<TreeNode> pre = new ArrayList<TreeNode>();
//        pre.add(root);
//        while (!pre.isEmpty()) {
//            List<TreeNode> current = new ArrayList<>();
//            List<Integer> result = new ArrayList<>();
//            for (int i = 0; i < pre.size(); i++) {
//                TreeNode node = pre.get(i);
//                result.add(node.val);
//                current.addAll(node.children);
//            }
//            results.add(result);
//            pre = current;
//        }
//        return results;
        /*
        方法三：递归
时间复杂度：O(n)。n 指的是节点的数量
空间复杂度：正常情况O(logn)，最坏情况 O(n)。运行时在堆栈上的空间。
         */
        if (root == null) return results;
        traverse(root, 0);
        return results;
    }

    private void traverse(TreeNode root, int level) {
        if (results.size() <= level) {
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        for (TreeNode node : root.children) {
            traverse(node, level + 1);
        }
    }

    //我们在树上使用基于队列的遍历算法,需要牢记的一个算法
    public void level(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode nextNode = queue.remove();
            values.add(nextNode.val);
            if (nextNode.children != null) {
                for (TreeNode child : nextNode.children) {
                    queue.add(child);
                }
            }
        }
    }

    public void postorderTraversal(TreeNode root) {
    /*
    方法一：递归
    时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
空间复杂度：O(n)。空间复杂度取决于递归的栈深度，为递归过程中栈的开销，平均情况下为 O(logn)，最坏情况下树呈现链状，为 O(n)。
     */
//        if (root == null) {
//            return;
//        }
//        postorderTraversal(root.left);
//        postorderTraversal(root.right);
//        res.add(root.val);
  /*
        方法二：迭代
时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
空间复杂度：O(n)。为迭代过程中显式栈的开销，平均情况下为 O(logn)，最坏情况下树呈现链状，为O(n)
         */
//        Deque<TreeNode> stack = new LinkedList<TreeNode>();
//        TreeNode pre = null;//代表访问过的上一个节点
//        while (root != null || !stack.isEmpty()) {
//            while (root != null) {
//                stack.push(root);
//                root = root.left;
//            }
//            root = stack.pop();
//            if (root.right == null || root.right == pre) {//root.right == prev代表该root节点的right已经访问过了
//                res.add(root.val);
//                pre = root;
//                root = null;
//            } else {//证明该节点有右节点且右节点还未访问过
//                stack.push(root);//需要重新将该根节点放置到stack中
//                root = root.right;
//            }
//        }
    /*
       方法三： Morris 中序遍历
时间复杂度：O(n)，其中 n 是二叉树的节点数。没有左子树的节点只被访问一次，有左子树的节点被访问两次。
空间复杂度：O(1)。只操作已经存在的指针（树的空闲指针），因此只需要常数的额外空间。
         */
        TreeNode p1 = root, predecessor = null;
        while (p1 != null) {
            predecessor = p1.left;
            if (predecessor != null) {
                while (predecessor.right != null && predecessor.right != p1) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    predecessor.right = null;
                    addPath(res, p1.left);
                }
            }
            p1 = p1.right;
        }
        addPath(res, root);
    }

    //按顺序正确保存跟和right的值，right在前，跟在后
    public void addPath(List<Integer> res, TreeNode node) {
        int count = 0;//保存的是此次需要调整的跟和right的数量
        while (node != null) {
            count++;
            res.add(node.val);
            node = node.right;
        }
        int left = res.size() - count, right = res.size() - 1;//此时res从left位置开始就是需要调整的跟节点和它的right（如果有right就一直right下去，没有左节点）
        while (left < right) {//将跟和right的值调换位置
            int temp = res.get(left);
            res.set(left, res.get(right));//set方法将index下标处的元素设置成element，并返回旧值oldValue
            res.set(right, temp);
            left++;
            right--;
        }
    }

    //144. 二叉树的前序遍历
    @Test
    public void preorderTraversal(TreeNode root) {
            /*
    方法一：递归
    时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
空间复杂度：O(n)。空间复杂度取决于递归的栈深度，为递归过程中栈的开销，平均情况下为 O(logn)，最坏情况下树呈现链状，为 O(n)。
     */
//        if (root == null) {
//            return;
//        }
//        res.add(root.val);
//        preorderTraversal(root.left);
//        preorderTraversal(root.right);
 /*
        方法二：迭代
时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
空间复杂度：O(n)。空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。
         */
//        Deque<TreeNode> stack = new LinkedList<TreeNode>();
//        while (root != null || !stack.isEmpty()) {
//            while (root != null) {
//                res.add(root.val);
//                stack.push(root);
//                root = root.left;
//            }
//            root = stack.pop();
//            root = root.right;
//        }
        /*
        方法三：Morris 遍历
时间复杂度：O(n)，其中 n 是二叉树的节点数。没有左子树的节点只被访问一次，有左子树的节点被访问两次。
空间复杂度：O(1)。只操作已经存在的指针（树的空闲指针），因此只需要常数的额外空间。
         */
        TreeNode predecessor = null;
        while (root != null) {
            predecessor = root.left;
            if (predecessor != null) {
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    res.add(root.val);
                    predecessor.right = root;
                    root = root.left;
                    continue;
                } else {//predecessor.right == root
                    predecessor.right = null;
                }
            } else { //左子树访问完
                res.add(root.val);
            }
            root = root.right;
        }
    }

    class ColorNode {
        TreeNode node;
        String color;//新节点为白色，已访问的节点为灰色;如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈;如果遇到的节点为灰色，则将节点的值输出。

        public ColorNode(TreeNode node, String color) {
            this.node = node;
            this.color = color;
        }
    }

    //94二叉树的中序遍历
    @Test
    public void inorderTraversal(TreeNode root) {
        /*
    方法一：递归
    时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
空间复杂度：O(n)。空间复杂度取决于递归的栈深度，为递归过程中栈的开销，平均情况下为 O(logn)，最坏情况下树呈现链状，为 O(n)。
     */
//        if (root == null) {
//            return;//return就是回到上层代码刚调用过的地方
//        }
//        inorderTraversal(root.left,res);
//        res.add(root.val);
//        inorderTraversal(root.right,res);
        /*
        方法二：迭代
时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
空间复杂度：O(n)。空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。
         */
//        Deque<TreeNode> stack = new LinkedList<TreeNode>();
//        while (root != null || !stack.isEmpty()) {
//            while (root != null) {//将该节点的和该节点的left依次存放在头结点
//                stack.push(root);//将node存放为头结点
//                root = root.left;
//            }
//            root = stack.pop();//移除头结点
//            res.add(root.val);
//            root = root.right;//将右子树存入
//        }
        /*
       方法三： Morris 中序遍历
       时间复杂度：O(n)，其中 n 为二叉搜索树的节点个数。Morris 遍历中每个节点会被访问两次，因此总时间复杂度为 O(2n)=O(n)。
空间复杂度：O(1)。
         */
//        TreeNode predecessor = null;
//        while (root != null) {//此种做法保证的节点x 的right就是x下一个应该访问的节点
//            predecessor = root.left; // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
//            if (predecessor != null) {
//                while (predecessor.right != null && predecessor.right != root) {
//                    predecessor = predecessor.right;
//                }
//                if (predecessor.right == null) { // 让 predecessor 的右指针指向 root
//                    predecessor.right = root;//因为predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止,所以按照中序遍历（左根右），即此时predecessor就是root的左子树的最后一个该访问的节点，下一个就是root
//                    root = root.left;//继续访问下一个左子树
//                } else {//说明左子树已经访问完了，我们需要断开链接//predecessor.right == root代表predecessor此颗子树已经访问完成，下一个应该访问的节点时root
//                    res.add(root.val);
//                    predecessor.right = null;
//                    root = root.right;//root.right是下一个应该访问访问的节点，所以赋值到节点root
//                }
//            } else {// 如果没有左孩子，则直接访问右孩子
//                res.add(root.val);//最左的孩子
//                root = root.right;//root.right是下一个应该访问访问的节点，所以赋值到节点root
//            }
//        }

        /*
        颜色标记法
        每个节点访问2次，stack存取2次
        时间复杂度：O（2n） 即 O（n）
        空间复杂度：O(n)。空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。
         */
        Deque<ColorNode> stack = new LinkedList<ColorNode>();
        if (root == null) return;
        ColorNode colorNode = new ColorNode(root, "white");
        stack.push(colorNode);
        while (!stack.isEmpty()) {
            ColorNode node = stack.pop();
            if (node == null) continue;
            if (node.color.equals("white")) {
                if (node.node.right != null) stack.push(new ColorNode(node.node.right, "white"));
                if (node.node != null) stack.push(new ColorNode(node.node, "gray"));
                if (node.node.left != null) stack.push(new ColorNode(node.node.left, "white"));
            } else {
                res.add(node.node.val);
            }
        }
    }
}
