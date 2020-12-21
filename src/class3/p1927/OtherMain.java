package class3.p1927;

import java.io.*;

public class OtherMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Heap heap = new Heap();

        for (int i = 0; i < n; i++) {
            int inputNumber = Integer.parseInt(br.readLine());
            if (inputNumber == 0) {
                if (heap.isEmpty()) {
                    bw.write("0\n");
                } else {
                    bw.write(heap.poll() + "\n");
                }
            } else {
                heap.offer(inputNumber);
            }
        }
        bw.flush();
    }

    static class Heap {
        int[] array;
        int size = 1;

        public Heap() {
            this.array = new int[100001];
        }

        public void offer(int e) {
            int index = size;
            array[size++] = e;

            while (index > 1 && array[index / 2] > array[index]) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        public int poll() {
            int result = array[1];

            swap(1, size - 1);
            size--;


            for (int i = 1, minIndex; i * 2 < size; i = minIndex) {
                minIndex = i * 2;

                if (i * 2 + 1 < size && array[minIndex] > array[minIndex + 1]) {
                    minIndex += 1;
                }

                if (array[i] < array[minIndex]) break;
                swap(i, minIndex);
            }

            return result;
        }

        public boolean isEmpty() {
            return size == 1;
        }

        private void swap(int child, int parent) {
            int temp = array[child];
            array[child] = array[parent];
            array[parent] = temp;
        }
    }
}
