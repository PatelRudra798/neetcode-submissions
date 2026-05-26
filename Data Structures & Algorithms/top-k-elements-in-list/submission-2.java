class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Pre-sized map avoids rehashing; merge() does single lookup+write
        Map<Integer, Integer> count = new HashMap<>(nums.length);
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }

        // Step 2: Build int[][] buckets — no ArrayList, no autoboxing
        // First pass: find max frequency + count elements per bucket
        int maxFreq = 0;
        for (int f : count.values()) {
            if (f > maxFreq) maxFreq = f;
        }

        int[] bucketSizes = new int[maxFreq + 1];
        for (int f : count.values()) bucketSizes[f]++;

        int[][] buckets = new int[maxFreq + 1][];
        for (int i = 1; i <= maxFreq; i++) {
            if (bucketSizes[i] > 0) buckets[i] = new int[bucketSizes[i]];
        }

        // Second pass: fill buckets using a position tracker
        int[] pos = new int[maxFreq + 1];
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            int f = e.getValue();
            buckets[f][pos[f]++] = e.getKey();
        }

        // Step 3: Collect top k (iterate only up to maxFreq, not nums.length)
        int[] result = new int[k];
        int idx = 0;
        for (int i = maxFreq; i >= 1 && idx < k; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    result[idx++] = num;
                    if (idx == k) return result;
                }
            }
        }

        return result;
    }
}
