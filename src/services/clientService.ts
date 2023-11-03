import { createClientHistory } from "../repository/clientRecordRepository";
import { findByUserId, update } from "../repository/clientRepository";
import { UpdateClientType } from "../schemas/clientSchema";

export const updateClient = async (
    loggedUserId: string,
    data: UpdateClientType
) => {
    const client = await findByUserId(loggedUserId);

    const lastClientRecord = client.clientRecords.reduce(
        (maxObject, currentObject) => {
            return currentObject.date > maxObject.date
                ? currentObject
                : maxObject;
        },
        client.clientRecords[0]
    );

    if (
        lastClientRecord.weight != data.weight ||
        lastClientRecord.height != data.height
    ) {
        createClientHistory({
            weight: data.weight,
            height: data.height,
            clientId: client.id,
        });
    }

    return update(client.id, data);
};
