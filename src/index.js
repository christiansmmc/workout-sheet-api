import fastifyJwt from "@fastify/jwt";
import "dotenv/config";
import Fastify from "fastify";
import authRoutes from "./routes/auth.js";
import userRoutes from "./routes/user.js";

const fastify = Fastify({
    logger: true,
});

fastify.register(fastifyJwt, { secret: process.env.JWT_SECRET });

fastify.register(authRoutes);
fastify.register(userRoutes);

fastify.decorate("authenticate", async function (request, reply) {
    try {
        request.jwtVerify();
    } catch (err) {
        reply.send(err);
    }
});

fastify.listen({ port: process.env.PORT || 3000 }, function (err, address) {
    if (err) {
        fastify.log.error(err);
        process.exit(1);
    }
});
