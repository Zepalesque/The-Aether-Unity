package net.zepalesque.unity.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.zepalesque.zenith.api.config.DataSerializableConfig;
import org.apache.commons.lang3.tuple.Pair;

public class UnityConfig {

    public static class Server extends DataSerializableConfig {

        public final ModConfigSpec.ConfigValue<Boolean> test;

        public Server(ModConfigSpec.Builder builder) {
            super(() -> SERVER_SPEC, "unity_server");
            builder.push("TODO");
            test = builder
                    .comment("not done")
                    .define("test", true);
            builder.pop();
        }
    }

    public static class Common extends DataSerializableConfig {

        public final ModConfigSpec.ConfigValue<Boolean> test;

        public Common(ModConfigSpec.Builder builder) {
            super(() -> COMMON_SPEC, "unity_common");
            builder.push("TODO");
            test = builder
                    .comment("not done")
                    .define("test", true);
            builder.pop();
        }
    }

    public static class Client {

        public final ModConfigSpec.ConfigValue<Boolean> test;

        public Client(ModConfigSpec.Builder builder) {
            builder.push("TODO");
            test = builder
                    .comment("not done")
                    .define("test", true);
            builder.pop();
        }
    }

    public static final ModConfigSpec COMMON_SPEC, SERVER_SPEC, CLIENT_SPEC;
    public static final Common COMMON;
    public static final Server SERVER;
    public static final Client CLIENT;

    static {
        final Pair<Server, ModConfigSpec> server = new ModConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = server.getRight();
        SERVER = server.getLeft();

        final Pair<Common, ModConfigSpec> common = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = common.getRight();
        COMMON = common.getLeft();

        final Pair<Client, ModConfigSpec> client = new ModConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = client.getRight();
        CLIENT = client.getLeft();
    }

}
