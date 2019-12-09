package dtos;

public class OptionCount {
    private Long optionId;
    private int count = 0;

    public OptionCount(long optionId, int count) {
        this.optionId = optionId;
        this.count = count;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrCount() {
        this.count++;
    }
}
