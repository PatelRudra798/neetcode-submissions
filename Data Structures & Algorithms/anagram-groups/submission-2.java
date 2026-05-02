class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            int[] freq = new int[26];  // constant space

            // Count characters → O(k)
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }

            // Build key efficiently
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                key.append(freq[i]).append('#');
            }

            // Store in hashmap
            map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(map.values());
    }

}