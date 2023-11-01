import fastifyJwt, { JWT } from "@fastify/jwt";
import { TypeBoxTypeProvider } from "@fastify/type-provider-typebox";
import "dotenv/config";
import Fastify, {
    FastifyInstance,
    FastifyReply,
    FastifyRequest,
} from "fastify";
import authRoutes from "./routes/auth";
import clientRoutes from "./routes/client";
import userRoutes from "./routes/user";

const fastify: FastifyInstance = Fastify({
    logger: true,
}).withTypeProvider<TypeBoxTypeProvider>();

declare module "@fastify/jwt" {
    interface FastifyJWT {
        user: {
            id: string;
        };
    }
}

declare module "fastify" {
    interface FastifyRequest {
        jwt: JWT;
    }
    export interface FastifyInstance {
        authenticate: any;
    }
}

fastify.register(fastifyJwt, { secret: process.env.JWT_SECRET as string });

fastify.register(authRoutes, { prefix: "api/authenticate" });
fastify.register(userRoutes, { prefix: "api/users" });
fastify.register(clientRoutes, { prefix: "api/clients" });

fastify.decorate(
    "authenticate",
    async (req: FastifyRequest, rep: FastifyReply) => {
        try {
            req.jwtVerify();
        } catch (err) {
            rep.send(err);
        }
    }
);

fastify.listen(
    { port: Number(process.env.PORT) || 3000 },
    function (err, address) {
        if (err) {
            fastify.log.error(err);
            process.exit(1);
        }
    }
);
