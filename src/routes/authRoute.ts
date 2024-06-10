import {FastifyInstance} from "fastify";
import {LoginType,} from "../schemas/userSchema";
import {authenticate} from "../services/authService";

export default async function (fastify: FastifyInstance) {
    fastify.post<{ Body: LoginType }>(
        "/",
        async (req, rep) => {
            const token = await authenticate(fastify, req.body);
            rep.status(200).send({access_token: token});
        }
    );
}
