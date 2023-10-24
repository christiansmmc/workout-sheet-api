import Fastify from "fastify";
import "dotenv/config";

const fastify = Fastify({
    logger: true,
});

fastify.listen({ port: process.env.PORT || 3000 }, function (err, address) {
    if (err) {
        fastify.log.error(err);
        process.exit(1);
    }
});
