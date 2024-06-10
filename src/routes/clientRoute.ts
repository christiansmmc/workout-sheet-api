import {FastifyInstance} from "fastify";
import {UpdateClientType,} from "../schemas/clientSchema";
import {updateClient} from "../services/clientService";

export default async function (fastify: FastifyInstance) {
    fastify.patch<{ Body: UpdateClientType }>(
        "/",
        {onRequest: [fastify.authenticate]},
        async (req, rep) => {
            const user = await updateClient(req.user.id, req.body);
            rep.status(200).send(user);
        }
    );
}
