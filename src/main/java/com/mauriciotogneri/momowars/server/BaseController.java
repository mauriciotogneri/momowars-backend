package com.mauriciotogneri.momowars.server;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.jerry.controller.Controller;
import com.mauriciotogneri.jerry.exceptions.client.BadRequestException;
import com.mauriciotogneri.jerry.exceptions.client.ConflictException;
import com.mauriciotogneri.jerry.exceptions.client.UnauthorizedException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.AccountAlreadyExistsException;
import com.mauriciotogneri.momowars.exceptions.AccountNotFoundException;
import com.mauriciotogneri.momowars.exceptions.InvalidCredentialsException;
import com.mauriciotogneri.momowars.exceptions.InvalidParametersException;
import com.mauriciotogneri.momowars.exceptions.InvalidSessionTokenException;
import com.mauriciotogneri.momowars.exceptions.MapNotFoundException;
import com.mauriciotogneri.momowars.logger.ErrorLogger;
import com.mauriciotogneri.momowars.repository.account.AccountDao;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

public class BaseController extends Controller
{
    protected static final String HEADER_SESSION_TOKEN = "Session-Token";

    protected Response process(ControllerImplementation controller) throws Exception
    {
        try
        {
            DatabaseConnection connection = new DatabaseConnection();

            try
            {
                Response response = controller.response(connection);

                connection.commit();

                return response;
            }
            catch (Exception e)
            {
                connection.rollback();

                throw e;
            }
            finally
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            throw processException(e);
        }
    }

    private Exception processException(Exception exception)
    {
        try
        {
            throw exception;
        }
        catch (InvalidParametersException e)
        {
            return new BadRequestException(e);
        }
        catch (AccountAlreadyExistsException e)
        {
            return new ConflictException(e);
        }
        catch (AccountNotFoundException | MapNotFoundException e)
        {
            return new NotFoundException(e);
        }
        catch (InvalidCredentialsException | InvalidSessionTokenException e)
        {
            return new UnauthorizedException(e);
        }
        catch (Exception e)
        {
            ErrorLogger.log(e);

            return e;
        }
    }

    protected void checkIfNotEmpty(Object entity)
    {
        if (entity == null)
        {
            throw new BadRequestException();
        }
    }

    protected void validateSessionToken(DatabaseConnection connection, String sessionToken) throws InvalidSessionTokenException, DatabaseException
    {
        AccountDao accountDao = new AccountDao(connection);

        accountDao.bySessionToken(sessionToken);
    }

    public interface ControllerImplementation
    {
        Response response(DatabaseConnection connection) throws Exception;
    }
}