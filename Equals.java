public class Equals {
        public static void main(String[] args) {

            CircularList<String> c0 = createList("A", "B", "C", "D", "E");
            CircularList<String> c1 = createList("A", "B", "C", "D");
            CircularList<String> c2 = createList(     "B", "C", "D", "E");
            CircularList<String> c3 = createList(     "B", "C", "D", "E", "A");

            CircularList<String> c4 = createList("A", "B", "C", "A", "B", "C");
            CircularList<String> c5 = createList("A", "B", "C");

            CircularList<String> c6 = createList("A");
            CircularList<String> c7 = createList("A", "A", "B", "C");

            test(c0, c1, false);
            test(c0, c2, false);
            test(c1, c2, false);
            test(c0, c3, true);
            test(c3, c0, true);
            test(c4, c5, false);
            test(c5, c4, false);
            test(c6, c7, false);
        }

        private static <T> void test(
                CircularList<T> c0, CircularList<T> c1, boolean expected) {
            boolean actual = c0.equals(c1);
            if (actual == expected) {
                System.out.print("PASSED");
            } else {
                System.out.print("FAILED");
            }
            System.out.println(" for " + toString(c0) + " and " + toString(c1));
        }

        private static <T> String toString(CircularList<T> c) {
            StringBuilder sb = new StringBuilder();
            Element<T> current = c.first;
            while (true) {
                sb.append(String.valueOf(current.info));
                current = current.next;
                if (current == c.first) {
                    break;
                } else {
                    sb.append(", ");
                }
            }
            return sb.toString();
        }

        private static CircularList<String> createList(String... elements) {
            CircularList<String> c = new CircularList<String>();
            for (String e : elements) {
                c.add(createElement(e));
            }
            return c;
        }

        private static <T> Element<T> createElement(T t) {
            Element<T> e = new Element<T>();
            e.info = t;
            return e;
        }
    }

    class Element<T> {
        Element<T> next;
        Element<T> previous;
        T info;
    }

    class CircularList<T> {
        Element<T> first = null;

        public void add(Element<T> e) {
            if (first == null) {
                first = e;
                e.next = e;
                e.previous = e;
            } else { // add to the end;
                first.previous.next = e;
                e.previous = first.previous;
                first.previous = e;
                e.next = first;
            }
        }

        public boolean equals(Object object) {
            if (this == object)
            {
                return true;
            }
            if (object == null)
            {
                return false;
            }
            if (!(object instanceof CircularList<?>))
            {
                return false;
            }
            CircularList<?> that = (CircularList<?>) object;

            Element<?> first0 = first;
            Element<?> current0 = first0;
            Element<?> first1 = that.first;
            Element<?> current1 = first1;
            while (true) {
                if (equalSequence(current0, current0, current1, current1)) {
                    return true;
                }
                current1 = current1.next;
                if (current1 == first1) {
                    return false;
                }
            }
        }

        private static boolean equalSequence(
                Element<?> first0, Element<?> current0,
                Element<?> first1, Element<?> current1) {
            while (true) {
                if (!equalElements(current0, current1)) {
                    return false;
                }
                current0 = current0.next;
                current1 = current1.next;
                if (current0 == first0 && current1 == first1) {
                    return true;
                }
                if (current0 == first0) {
                    return false;
                }
                if (current1 == first1) {
                    return false;
                }
            }
        }

        private static boolean equalElements(Element<?> t0, Element<?> t1) {
            if (t0 == null) {
                return t1 == null;
            }
            return equal(t0.info, t1.info);
        }

        private static <T> boolean equal(T t0, T t1) {
            if (t0 == null) {
                return t1 == null;
            }
            return t0.equals(t1);
        }

    }
