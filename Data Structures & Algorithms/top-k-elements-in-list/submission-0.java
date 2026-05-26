class Solution {
    public int[] topKFrequent(int[] nums, int k) {
                Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // Step 2: Build frequency buckets
        // Index i holds all numbers that appear exactly i times
        List<Integer>[] freq = new List[nums.length + 1];
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            freq[entry.getValue()].add(entry.getKey());
        }

        // Step 3: Collect top k elements (highest frequency first)
        int[] result = new int[k];
        int idx = 0;
        for (int i = freq.length - 1; i >= 1 && idx < k; i--) {
            for (int num : freq[i]) {
                result[idx++] = num;
                if (idx == k) return result;
            }
        }

        return result;
    }
}
