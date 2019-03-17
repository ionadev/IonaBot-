package com.ionabot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.ChannelType;

import java.awt.*;


public class CatCommand extends Command
{
    public CatCommand()
    {
        this.name = "cat";
        this.help = "Shows A Random Cat";
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event)
    {
        Unirest.get("http://aws.random.cat/meow").asJsonAsync(new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response)
            {
                event.reply(new EmbedBuilder()
                .setColor(event.isFromType(ChannelType.TEXT) ? event.getSelfMember().getColor() : Color.GREEN)
                .setImage(response.getBody().getObject().getString("file"))
                .build());
            }

            @Override
            public void failed(UnirestException e) {
                event.reactError();
            }

            @Override
            public void cancelled() {
                event.reactError();
            }
        });
    }
}
