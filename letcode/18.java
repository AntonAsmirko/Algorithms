//один - як два - ду  три - се   четыре - чор  пять - пандж   шесть - шаш   семь - хафт   восемь - хашт  девять - нух   десть - дах
class Solution {
    
    private void undoIt(HashMap<Integer, Integer> mapp, LinkedList<Integer> q) {
        for (Integer i : q) {
            mapp.put(i, mapp.get(i) + 1);
        }
    }

    private boolean take(Integer num, HashMap<Integer, Integer> canTake, LinkedList<Integer> undo, List<Integer> tmp) {
        if (canTake.get(num) == 0) {
            undoIt(canTake, undo);
            return false;
        } else {
            canTake.put(num, canTake.get(num) - 1);
            undo.add(num);
        }
        tmp.add(num);
        return true;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<Pair> tmmp = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                tmmp.add(new Pair(nums[i], nums[j]));
            }
        }
        Pair[] uniqueArray = tmmp.toArray(new Pair[tmmp.size()]);
        List<List<Integer>> result = new LinkedList<>();
        HashMap<Integer, Integer> canTake = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (canTake.containsKey(nums[i])) {
                canTake.put(nums[i], canTake.get(nums[i]) + 1);
            } else {
                canTake.put(nums[i], 1);
            }
        }
        for (int i = 0; i < uniqueArray.length; i++) {
            outer:
            for (int j = 0; j < i; j++) {
                if (uniqueArray[i].f + uniqueArray[i].s + uniqueArray[j].f + uniqueArray[j].s == target) {
                    List<Integer> tmp = new LinkedList<>();
                    LinkedList<Integer> undo = new LinkedList<>();
                    if (!take(uniqueArray[i].f, canTake, undo, tmp))
                        continue;
                    if (!take(uniqueArray[i].s, canTake, undo, tmp))
                        continue;
                    if (!take(uniqueArray[j].f, canTake, undo, tmp))
                        continue;
                    if (!take(uniqueArray[j].s, canTake, undo, tmp))
                        continue;
                    Collections.sort(tmp, Comparator.naturalOrder());
                    undoIt(canTake, undo);
                    TreeSet<Integer> tmpSet = new TreeSet(tmp);
                    for (List<Integer> list : result) {
                        if (tmpSet.containsAll(list) && (new TreeSet<>(list)).containsAll(tmpSet)) {
                            continue outer;
                        }
                    }
                    result.add(tmp);
                }
            }
        }
        return result;
    }

    private static class Pair {
        int f;
        int s;

        public Pair(int f, int s) {
            this.f = f;
            this.s = s;
        }
    }
}