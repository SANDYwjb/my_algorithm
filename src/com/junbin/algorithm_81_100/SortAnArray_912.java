package com.junbin.algorithm_81_100;

/**
 * 912. 排序数组-中等
 * 给你一个整数数组 nums,请你将该数组升序排列。
 * 思路1：快速排序
 * 快速排序的核心思想也是分治法,分而治之。它的实现方式是每次从序列中选出一个基准值,其他数依次和基准值做比较,比基准值大的放右边,比基准值小的放左边,
 * 然后再对左边和右边的两组数分别选出一个基准值,进行同样的比较移动,重复步骤,直到最后都变成单个元素,整个数组就成了有序的序列。
 * 时间复杂度O(nlogn),空间复杂度：O(h)
 * 思路2：堆排序
 * 堆是一种优先队列,两种实现,最大堆和最小堆,由于我们这里排序按升序排,所以就直接以最大堆来说吧。
 * 我们完全可以把堆（以下全都默认为最大堆）看成一棵完全二叉树,但是位于堆顶的元素总是整棵树的最大值,每个子节点的值都比父节点小,
 * 由于堆要时刻保持这样的规则特性,所以一旦堆里面的数据发生变化,我们必须对堆重新进行一次构建。
 * 既然堆顶元素永远都是整棵树中的最大值,那么我们将数据构建成堆后,只需要从堆顶取元素不就好了吗？第一次取的元素,是否取的就是最大值？
 * 取完后把堆重新构建一下,然后再取堆顶的元素,是否取的就是第二大的值？反复的取,取出来的数据也就是有序的数据。
 * 时间复杂度Ο(nlogn)
 * <p>
 * 思路3：归并排序
 * 归并字面上的意思是合并,归并算法的核心思想是分治法,就是将一个数组一刀切两半,递归切,直到切成单个元素,然后重新组装合并,单个元素合并成小数组,
 * 两个小数组合并成大数组,直到最终合并完成,排序完毕。
 * 时间复杂度Ο(nlogn)
 *
 * @author junbin.wang
 * @date 2023/2/25上午11:04
 */
public class SortAnArray_912 {
    public static class QuickSort {
        public static void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private static void sort(int[] arr, int startIndex, int endIndex) {
            if (endIndex <= startIndex) {
                return;
            }
            //切分
            int pivotIndex = partition(arr, startIndex, endIndex);
            sort(arr, startIndex, pivotIndex - 1);
            sort(arr, pivotIndex + 1, endIndex);
        }

        private static int partition(int[] arr, int startIndex, int endIndex) {
            int left = startIndex;
            int right = endIndex;
            //取第一个元素为基准值
            int pivot = arr[startIndex];

            while (true) {
                //从左往右扫描
                while (arr[left] <= pivot) {
                    left++;
                    if (left == right) {
                        break;
                    }
                }
                //从右往左扫描
                while (pivot < arr[right]) {
                    right--;
                    if (left == right) {
                        break;
                    }
                }
                //左右指针相遇
                if (left >= right) {
                    break;
                }
                //交换左右数据：此时arr[left] > pivot 且 arr[right] < pivot
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
            //将基准值插入序列（指针重合处）,这里用pivot更好理解,但是arr[startIndex]也可以,因为arr[startIndex]肯定和pivot一样,
            // 上面的扫描规则可知
            int temp = arr[startIndex];
            arr[startIndex] = arr[right];
            arr[right] = temp;
            return right;
        }
    }

    public static class HeapSort {
        /**
         * 堆排序,下面看不懂可以用8,2,5,9,7,3画图看看
         */
        public static void heapSort(int[] arr) {
            //为什么从arr.length/2-1开始？由于堆排序近似完全二叉树.假设最后一个非叶子结点下标为n
            //当它的左子结点为末尾元素时：2n+1 = length-1 ==> n = length/2-1
            //当它的右子结点为末尾元素时：2n+2 = length-1 ==> n = length/2-(3/2)
            //在计算机中3/2是等于1的,所以从arr.length/2-1
            for (int i = arr.length / 2 - 1; i >= 0; i--) {
                adjustHeap(arr, i, arr.length);
            }

            for (int j = arr.length - 1; j > 0; j--) {
                int temp = arr[j];
                arr[j] = arr[0];
                arr[0] = temp;
            /*为什么从0开始？
                因为在第一次构建大顶堆后让堆顶元素和末尾元素进行交换
                而对于其他的非叶子结点所对应的子树都是大顶堆就无需调整,
                只需要堆顶元素(下标为0的非叶子结点)的子树调整成大顶堆
            */
                adjustHeap(arr, 0, j);

            }
        }

        /**
         * 构建大顶堆
         * 注意：
         * 这个方法并不是将整个树调整成大顶堆
         * 而是以i对应的非叶子结点的子树调整成大顶堆
         *
         * @param arr    待调整的数组
         * @param parent 非叶子结点在数组中的索引(下标)
         * @param length 进行调整的元素的个数,length是在逐渐减少的
         */
        public static void adjustHeap(int[] arr, int parent, int length) {
            /*取出当前非叶子结点的值保到临时变量中*/
            int temp = arr[parent];

            /*j=parent*2+1表示的是parent结点的左子结点*/
            for (int j = parent * 2 + 1; j < length; j = j * 2 + 1) {
                //左子结点小于右子结点
                if (j + 1 < length && arr[j] < arr[j + 1]) {
                    //j指向右子结点
                    j++;
                }
                //子节点大于父节点
                if (arr[j] > temp) {
                    //把较大的值赋值给父节点
                    arr[parent] = arr[j];
                    //arr[j] = temp; 这里没必要换
                    parent = j; //让parent指向与其换位的子结点
                } else {
                    /*子树已经是大顶堆了*/
                    break;
                }
            }
            arr[parent] = temp;
        }
    }

    public static class MergeSort {
        public static void sort(int[] arr) {
            int[] tempArr = new int[arr.length];
            sort(arr, tempArr, 0, arr.length - 1);
        }

        /**
         * 归并排序
         *
         * @param arr        排序数组
         * @param tempArr    临时存储数组
         * @param startIndex 排序起始位置
         * @param endIndex   排序终止位置
         */
        private static void sort(int[] arr, int[] tempArr, int startIndex, int endIndex) {
            if (endIndex <= startIndex) {
                return;
            }
            //中部下标
            int middleIndex = startIndex + (endIndex - startIndex) / 2;

            //分解
            sort(arr, tempArr, startIndex, middleIndex);
            sort(arr, tempArr, middleIndex + 1, endIndex);

            //归并
            merge(arr, tempArr, startIndex, middleIndex, endIndex);
        }

        /**
         * 归并
         *
         * @param arr         排序数组
         * @param tempArr     临时存储数组
         * @param startIndex  归并起始位置
         * @param middleIndex 归并中间位置
         * @param endIndex    归并终止位置
         */
        private static void merge(int[] arr, int[] tempArr, int startIndex, int middleIndex, int endIndex) {
            //复制要合并的数据
            for (int s = startIndex; s <= endIndex; s++) {
                tempArr[s] = arr[s];
            }

            int left = startIndex;//左边首位下标
            int right = middleIndex + 1;//右边首位下标
            for (int k = startIndex; k <= endIndex; k++) {
                if (left > middleIndex) {
                    //如果左边的首位下标大于中部下标,证明左边的数据已经排完了。
                    arr[k] = tempArr[right++];
                } else if (right > endIndex) {
                    //如果右边的首位下标大于了数组长度,证明右边的数据已经排完了。
                    arr[k] = tempArr[left++];
                } else if (tempArr[right] < tempArr[left]) {
                    arr[k] = tempArr[right++];//将右边的首位排入,然后右边的下标指针+1。
                } else {
                    arr[k] = tempArr[left++];//将左边的首位排入,然后左边的下标指针+1。
                }
            }
        }
    }
}
