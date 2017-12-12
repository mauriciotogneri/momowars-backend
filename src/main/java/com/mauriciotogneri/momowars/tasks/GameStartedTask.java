package com.mauriciotogneri.momowars.tasks;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.email.Email;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.repository.account.AccountDao;

import java.util.List;

public class GameStartedTask extends Task
{
    private final Long gameId;

    public GameStartedTask(Long gameId)
    {
        this.gameId = gameId;
    }

    @Override
    public void process(DatabaseConnection connection) throws Exception
    {
        AccountDao accountDao = new AccountDao(connection);
        List<Account> accounts = accountDao.byGame(gameId);

        for (Account account : accounts)
        {
            String content = String.format("The game <b>%s</b> has started.", gameId);
            Email email = new Email(Email.DEFAULT_ADDRESS,
                                    Email.DEFAULT_ALIAS,
                                    account.email(),
                                    "A game has started!",
                                    content);

            Task task = new SendEmailTask(email);
            task.submit();
        }
    }
}