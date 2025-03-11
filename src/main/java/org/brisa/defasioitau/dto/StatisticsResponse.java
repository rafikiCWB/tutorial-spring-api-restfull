package org.brisa.defasioitau.dto;

import lombok.Getter;

import java.util.DoubleSummaryStatistics;

@Getter
public class StatisticsResponse {

    private final long count;
    private final double sum;
    private final double avg;
    private final double min;
    private final double maximo;

    public StatisticsResponse(DoubleSummaryStatistics status) {
        this.count = status.getCount();
        this.sum = status.getSum();
        this.avg = status.getAverage();
        this.min = status.getMin();
        this.maximo = status.getMax();
    }

}
