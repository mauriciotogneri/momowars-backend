package com.mauriciotogneri.momowars.server;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.jerry.controller.Controller;
import com.mauriciotogneri.jerry.exceptions.client.BadRequestException;
import com.mauriciotogneri.jerry.exceptions.client.ConflictException;
import com.mauriciotogneri.jerry.exceptions.client.ForbiddenException;
import com.mauriciotogneri.jerry.exceptions.client.UnauthorizedException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.AccountAlreadyExistsException;
import com.mauriciotogneri.momowars.exceptions.AccountNotFoundException;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.GameFullException;
import com.mauriciotogneri.momowars.exceptions.GameNotFoundException;
import com.mauriciotogneri.momowars.exceptions.GameNotOpenException;
import com.mauriciotogneri.momowars.exceptions.InvalidCredentialsException;
import com.mauriciotogneri.momowars.exceptions.InvalidGameException;
import com.mauriciotogneri.momowars.exceptions.InvalidParametersException;
import com.mauriciotogneri.momowars.exceptions.InvalidTokenException;
import com.mauriciotogneri.momowars.exceptions.MapNotFoundException;
import com.mauriciotogneri.momowars.exceptions.PlayerAlreadyJoinedException;
import com.mauriciotogneri.momowars.exceptions.PlayerNotFoundException;
import com.mauriciotogneri.momowars.logger.ErrorLogger;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.repository.account.AccountDao;
import com.mauriciotogneri.momowars.templates.BaseTemplate;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class BaseController extends Controller
{
    protected static final String HEADER_SESSION_TOKEN = "Session-Token";

    protected static final String PARAM_GAME_ID = "gameId";
    protected static final String PARAM_MAP_ID = "mapId";
    protected static final String PARAM_PLAYER_ID = "playerId";

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
        catch (AccountAlreadyExistsException | PlayerAlreadyJoinedException | GameNotOpenException | GameFullException e)
        {
            return new ConflictException(e);
        }
        catch (AccountNotFoundException | MapNotFoundException | GameNotFoundException | PlayerNotFoundException e)
        {
            return new NotFoundException(e);
        }
        catch (InvalidCredentialsException | InvalidTokenException e)
        {
            return new UnauthorizedException(e);
        }
        catch (InvalidGameException e)
        {
            return new ForbiddenException(e);
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

    protected Account validateSessionToken(DatabaseConnection connection, String sessionToken) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);

        return accountDao.bySessionToken(sessionToken);
    }

    protected Response htmlResponse(BaseTemplate template) throws Exception
    {
        return Response
                .status(Status.OK)
                .header("Content-Type", "text/html; charset=utf-8")
                .entity(template.render())
                .build();
    }

    public interface ControllerImplementation
    {
        Response response(DatabaseConnection connection) throws Exception;
    }
}