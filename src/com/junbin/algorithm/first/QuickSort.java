package com.junbin.algorithm.first;

/**
 * 手撕快速排序
 * 快速排序实际上是属于交换类的排序，只是它通过多次划分操作实现排序。这就是分治思想，把一个序列分成两个子序列它每一趟选择序列中的一个关键字作为枢轴，将序列中比枢轴小的移到前面，大的移到后边。当本趟所有子序列都被枢轴划分完毕后得到一组更短的子序列，成为下一趟划分的初始序列集。每一趟结束后都会有一个关键字达到最终位置。
 *
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/13下午9:35
 */
public class QuickSort {
    /**
     * 快速排序算是在冒泡排序的基础上的递归分治交换排序
     *
     * @param A    待排数组
     * @param low  数组起点
     * @param high 数组终点
     */
    public static void QuickSort(int[] A, int low, int high) {
        if (low >= high) {                             //递归分治完成退出
            return;
        }
        int left = low;                               //设置左遍历指针 left
        int right = high;                             //设置右遍历指针 right
        int pivot = A[left];                          //设置枢轴 pivot, 默认是数组最左端的值
        while (left < right) {                         //循环条件
            while (left < right && A[right] >= pivot) { //若右指针所指向元素大于枢轴值，则右指针向左移动
                right--;
            }
            A[left] = A[right];                       //反之替换，这里其实不会导致left位置的值被覆盖
            while (left < right && A[left] <= pivot) {//若左指针所指向元素小于枢轴值，则左指针向右移动
                left++;
            }
            A[right] = A[left];                       //反之替换
        }
        A[left] = pivot;                              //将枢轴值放在最终位置上
        QuickSort(A, low, left - 1);            //依此递归枢轴值左侧的元素
        QuickSort(A, left + 1, high);            //依此递归枢轴值右侧的元素
    }
}
