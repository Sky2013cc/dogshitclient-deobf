package com.sun.tools.javac.util;

/* loaded from: target.jar:com/sun/tools/javac/util/GraphUtils.class */
public class GraphUtils {

    /* loaded from: target.jar:com/sun/tools/javac/util/GraphUtils$DependencyKind.class */
    public interface DependencyKind {
        String getDotStyle();
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/GraphUtils$Node.class */
    public static abstract class Node<D> {
        public final D data;

        public abstract DependencyKind[] getSupportedDependencyKinds();

        public abstract Iterable<? extends Node<D>> getAllDependencies();

        public abstract String getDependencyName(Node<D> node, DependencyKind dependencyKind);

        public Node(D d) {
            this.data = d;
        }

        public String toString() {
            return this.data.toString();
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/GraphUtils$TarjanNode.class */
    public static abstract class TarjanNode<D> extends Node<D> implements Comparable<TarjanNode<D>> {
        int index;
        int lowlink;
        boolean active;

        @Override // com.sun.tools.javac.util.GraphUtils.Node
        public abstract Iterable<? extends TarjanNode<D>> getAllDependencies();

        public abstract Iterable<? extends TarjanNode<D>> getDependenciesByKind(DependencyKind dependencyKind);

        public TarjanNode(D d) {
            super(d);
            this.index = -1;
        }

        @Override // java.lang.Comparable
        public int compareTo(TarjanNode<D> tarjanNode) {
            if (this.index < tarjanNode.index) {
                return -1;
            }
            return this.index == tarjanNode.index ? 0 : 1;
        }
    }

    public static <D, N extends TarjanNode<D>> List<? extends List<? extends N>> tarjan(Iterable<? extends N> iterable) {
        return new Tarjan().findSCC(iterable);
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/GraphUtils$Tarjan.class */
    private static class Tarjan<D, N extends TarjanNode<D>> {
        int index;
        ListBuffer<List<N>> sccs;
        ListBuffer<N> stack;

        private Tarjan() {
            this.index = 0;
            this.sccs = new ListBuffer<>();
            this.stack = new ListBuffer<>();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<? extends List<? extends N>> findSCC(Iterable<? extends N> iterable) {
            for (N n : iterable) {
                if (n.index == -1) {
                    findSCC((Tarjan<D, N>) n);
                }
            }
            return this.sccs.toList();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void findSCC(N n) {
            visitNode(n);
            for (TarjanNode<D> tarjanNode : n.getAllDependencies()) {
                if (tarjanNode.index != -1) {
                    if (this.stack.contains(tarjanNode)) {
                        n.lowlink = Math.min(n.lowlink, tarjanNode.index);
                    }
                } else {
                    findSCC((Tarjan<D, N>) tarjanNode);
                    n.lowlink = Math.min(n.lowlink, tarjanNode.lowlink);
                }
            }
            if (n.lowlink == n.index) {
                addSCC(n);
            }
        }

        private void visitNode(N n) {
            n.index = this.index;
            n.lowlink = this.index;
            this.index++;
            this.stack.prepend(n);
            n.active = true;
        }

        private void addSCC(N n) {
            N remove;
            ListBuffer listBuffer = new ListBuffer();
            do {
                remove = this.stack.remove();
                remove.active = false;
                listBuffer.add(remove);
            } while (remove != n);
            this.sccs.add(listBuffer.toList());
        }
    }

    public static <D> String toDot(Iterable<? extends TarjanNode<D>> iterable, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("digraph %s {\n", str));
        sb.append(String.format("label = \"%s\";\n", str2));
        for (TarjanNode<D> tarjanNode : iterable) {
            sb.append(String.format("%s [label = \"%s\"];\n", Integer.valueOf(tarjanNode.hashCode()), tarjanNode.toString()));
        }
        for (TarjanNode<D> tarjanNode2 : iterable) {
            for (DependencyKind dependencyKind : tarjanNode2.getSupportedDependencyKinds()) {
                for (TarjanNode<D> tarjanNode3 : tarjanNode2.getDependenciesByKind(dependencyKind)) {
                    sb.append(String.format("%s -> %s [label = \" %s \" style = %s ];\n", Integer.valueOf(tarjanNode2.hashCode()), Integer.valueOf(tarjanNode3.hashCode()), tarjanNode2.getDependencyName(tarjanNode3, dependencyKind), dependencyKind.getDotStyle()));
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
}
