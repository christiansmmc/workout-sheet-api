import { FastifyInstance } from "fastify";
import {
    UpdateClientResponseSchema,
    UpdateClientSchema,
    UpdateClientType,
} from "../schemas/clientSchema";
import { updateClient } from "../services/clientService";

export default async function (fastify: FastifyInstance) {
    fastify.patch<{ Body: UpdateClientType }>(
        "/",
        {
            onRequest: [fastify.authenticate],
            schema: {
                body: UpdateClientSchema,
                response: { 200: UpdateClientResponseSchema },
            },
        },
        async (req, rep) => {
            const user = await updateClient(req.user.id, req.body);
            rep.status(200).send(user);
        }
    );
}
