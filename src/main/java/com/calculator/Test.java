package com.calculator;

import java.util.Arrays;

/**
 * @author fengjian
 * @since 2020/11/19
 */
public class Test {

    public static void main(String[] args) {
        int[] arr = new int[]{34, 23, 54, 7, 6, 23, 3, 1, 80};
        System.out.println(Arrays.toString(arr));

      /*  chooseSort(arr);
        System.out.println("选择排序结果：");
        System.out.println(Arrays.toString(arr));*/

       /* insertSort(arr);
        System.out.println("插入排序结果：");
        System.out.println(Arrays.toString(arr));*/

      /*  bubbleSort(arr);
        System.out.println("冒泡排序结果：");
        System.out.println(Arrays.toString(arr));*/

        quickSort(arr);
        System.out.println("快速排序结果：");
        System.out.println(Arrays.toString(arr));

        /*arr = mergeSort(arr);
        System.out.println("归并排序结果：");
        System.out.println(Arrays.toString(arr));*/

    }


    /**
     * 快排
     *
     * @param arr
     * @return
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        //基准位置
        int temp = arr[start];
        int left = start;
        int right = end;
        while (left < right) {
            //先看右边，一次往左递减
            while (temp <= arr[right] && left < right) {
                right--;
            }
            arr[left] = arr[right];
            while (temp >= arr[left] && left < right) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        quickSort(arr, start, right - 1);
        quickSort(arr, left + 1, end);
    }

    /**
     * 选择排序
     *
     * @param arr
     * @return
     */
    public static void chooseSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

    }

    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public static int[] mergeSort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }
        int halfSize = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, halfSize);
        int[] right = Arrays.copyOfRange(arr, halfSize, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int resultIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }
        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }
        return result;
    }

}
