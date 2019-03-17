import com.ionabot.commands.CatCommand;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Paths;

public class IonaBot
{
    public static void main(String[] args) throws IOException, LoginException,IllegalArgumentException, RateLimitedException
    {
        List<String> list = Files.readAllLines(Paths.get("config.txt"));
        String token = list.get(0);
        String ownerId = list.get(1);

        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder();
        client.useDefaultGame();
        client.setOwnerId(ownerId);
        client.setPrefix(")");
        client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");

        client.addCommands(
                new CatCommand()
        );

        new JDABuilder(AccountType.BOT)
                .setToken(token)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .addEventListener(waiter)
                .addEventListener(client.build())
                .build();
    }
}
