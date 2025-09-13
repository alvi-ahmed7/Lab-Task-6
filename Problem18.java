import java.util.*;

// Enum for colors
enum Color {
    RED, GREEN
}

// Abstract Tree class
abstract class Tree {
    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

// TreeNode class (non-leaf)
class TreeNode extends Tree {
    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void addChild(Tree child) {
        children.add(child);
    }

    public ArrayList<Tree> getChildren() {
        return children;
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);
        for (Tree child : children) {
            child.accept(visitor);
        }
    }
}

// TreeLeaf class (leaf)
class TreeLeaf extends Tree {
    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

// Abstract visitor
abstract class TreeVis {
    public abstract int getResult();

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);
}

// Visitor 1: Sum of leaf values
class SumInLeavesVisitor extends TreeVis {
    private int sum = 0;

    public int getResult() {
        return sum;
    }

    public void visitNode(TreeNode node) {
        // nothing to do for non-leaf
    }

    public void visitLeaf(TreeLeaf leaf) {
        sum += leaf.getValue();
    }
}

// Visitor 2: Product of red node values (mod 1e9+7)
class ProductRedNodesVisitor extends TreeVis {
    private long product = 1;
    private static final int MOD = 1000000007;

    public int getResult() {
        return (int) product;
    }

    public void visitNode(TreeNode node) {
        if (node.getColor() == Color.RED) {
            product = (product * node.getValue()) % MOD;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.RED) {
            product = (product * leaf.getValue()) % MOD;
        }
    }
}

// Visitor 3: FancyVisitor
class FancyVisitor extends TreeVis {
    private int evenDepthNonLeafSum = 0;
    private int greenLeafSum = 0;

    public int getResult() {
        return Math.abs(evenDepthNonLeafSum - greenLeafSum);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            evenDepthNonLeafSum += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.GREEN) {
            greenLeafSum += leaf.getValue();
        }
    }
}

// Main solution class
public class Problem18 {
    private static int[] values;
    private static Color[] colors;
    private static Map<Integer, Set<Integer>> edges;

    public static Tree solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }

        colors = new Color[n];
        for (int i = 0; i < n; i++) {
            int c = sc.nextInt();
            colors[i] = (c == 0 ? Color.RED : Color.GREEN);
        }

        edges = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            edges.put(i, new HashSet<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges.get(u).add(v);
            edges.get(v).add(u);
        }
        sc.close();

        return buildTree(1, 0); // root is always node 1
    }

    private static Tree buildTree(int nodeId, int depth) {
        Set<Integer> neighbors = edges.get(nodeId);
        if (neighbors.isEmpty()) {
            return new TreeLeaf(values[nodeId - 1], colors[nodeId - 1], depth);
        }

        TreeNode node = new TreeNode(values[nodeId - 1], colors[nodeId - 1], depth);
        for (int child : new HashSet<>(neighbors)) {
            edges.get(child).remove(nodeId); // prevent going back up
            node.addChild(buildTree(child, depth + 1));
        }

        return node;
    }

    // main for testing (HackerRank locked code will call visitors)
    public static void main(String[] args) {
        Tree root = solve();

        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductRedNodesVisitor vis2 = new ProductRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        System.out.println(vis1.getResult());
        System.out.println(vis2.getResult());
        System.out.println(vis3.getResult());
    }
}
