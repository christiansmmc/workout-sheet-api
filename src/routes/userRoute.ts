import {FastifyInstance} from "fastify";
import {CreateAccountType,} from "../schemas/userSchema";
import {createUser, findUserById} from "../services/userService";

export default async function (fastify: FastifyInstance) {
    fastify.post<{ Body: CreateAccountType }>(
        "/",
        async (req, rep) => {
            const user = await createUser(req.body);
            rep.status(201).send(user);
        }
    );

    fastify.get(
        "/",
        {onRequest: [fastify.authenticate]},
        async (req, rep) => {
            const user = await findUserById(req.user.id);
            rep.status(200).send(user);
        }
    );
}
