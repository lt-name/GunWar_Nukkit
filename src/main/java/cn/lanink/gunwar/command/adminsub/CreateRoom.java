package cn.lanink.gunwar.command.adminsub;

import cn.lanink.gunwar.command.base.BaseSubCommand;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.level.Level;

/**
 * @author lt_name
 */
public class CreateRoom extends BaseSubCommand {

    public CreateRoom(String name) {
        super(name);
    }

    @Override
    public boolean canUser(CommandSender sender) {
        return sender.isPlayer() && sender.isOp();
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        Player player = (Player) sender;
        if (!this.gunWar.getRooms().containsKey(args[1])) {
            Level level = Server.getInstance().getLevelByName(args[1]);
            if (level != null) {
                this.gunWar.getRoomConfig(args[1]);
                player.teleport(level.getSafeSpawn());
                Server.getInstance().dispatchCommand(player,
                        this.gunWar.getCmdAdmin() + " SetRoom " + args[1]);
            }
        }else {
            sender.sendMessage(this.language.admin_createRoom_exist);
        }
        return true;
    }

    @Override
    public CommandParameter[] getParameters() {
        return new CommandParameter[]{ CommandParameter.newType("roomName", CommandParamType.TEXT) };
    }

}
