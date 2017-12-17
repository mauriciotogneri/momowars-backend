package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.json.AccountMatchesJson;
import com.mauriciotogneri.momowars.types.MatchStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountMatches
{
    public Integer[] ids = new Integer[0];
    public MatchStatus[] statuses = new MatchStatus[0];

    private List<Long> filter(Integer[] matches, MatchStatus[] statuses, MatchStatus matchStatus)
    {
        List<Long> result = new ArrayList<>();

        for (int i = 0; i < matches.length; i++)
        {
            Integer matchId = matches[i];
            MatchStatus status = statuses[i];

            if (matchStatus.equals(status))
            {
                result.add(new Long(matchId));
            }
        }

        return result;
    }

    public boolean hasMatch(Long matchId)
    {
        Integer intValue = matchId.intValue();

        return Arrays.stream(ids).anyMatch(intValue::equals);
    }

    public AccountMatchesJson json()
    {
        List<Long> open = filter(ids, statuses, MatchStatus.OPEN);

        List<Long> playing = new ArrayList<>();
        playing.addAll(filter(ids, statuses, MatchStatus.PLAYING));
        playing.addAll(filter(ids, statuses, MatchStatus.CALCULATING));

        List<Long> finished = filter(ids, statuses, MatchStatus.FINISHED);

        return new AccountMatchesJson(open,
                                      playing,
                                      finished);
    }
}