package com.mauriciotogneri.momowars.tasks;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.email.Email;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.repository.account.AccountDao;

import java.util.List;

public class MatchStartedTask extends Task
{
    private final Long matchId;

    public MatchStartedTask(Long matchId)
    {
        this.matchId = matchId;
    }

    @Override
    public void process(DatabaseConnection connection) throws Exception
    {
        AccountDao accountDao = new AccountDao(connection);
        List<Account> accounts = accountDao.byMatch(matchId);

        for (Account account : accounts)
        {
            String content = String.format("The match <b>%s</b> has started.", matchId);
            Email email = new Email(Email.DEFAULT_ADDRESS,
                                    Email.DEFAULT_ALIAS,
                                    account.email(),
                                    "A match has started!",
                                    content);

            Task task = new SendEmailTask(email);
            task.submit();
        }
    }
}