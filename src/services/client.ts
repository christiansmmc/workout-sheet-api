import { findByUserId, update } from "../repository/client";
import { createClientHistory } from "../repository/clientHistory";
import { UpdateClientType } from "../schemas/client";

export const updateClient = async (
    loggedUserId: string,
    data: UpdateClientType
) => {
    const client = await findByUserId(loggedUserId);

    const lastClientHistory = client?.clientHistory.reduce(
        (maxObject, currentObject) => {
            return currentObject.date > maxObject.date
                ? currentObject
                : maxObject;
        },
        client?.clientHistory[0]
    );

    if (
        lastClientHistory.weight != data.weight ||
        lastClientHistory.height != data.height
    ) {
        createClientHistory({
            weight: data.weight,
            height: data.height,
            client_id: client.id,
        });
    }

    return update(client.id, data);
};
