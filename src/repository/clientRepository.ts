import prisma from "../config/prisma";
import { UpdateClientType } from "../schemas/clientSchema";

export const findByUserId = async (userId: string) => {
    const client = await prisma.client.findUnique({
        where: { userId },
        include: { clientRecords: true },
    });

    if (!client) {
        throw new Error("User not found");
    }

    return client;
};

export const update = async (id: string, data: UpdateClientType) =>
    await prisma.client.update({
        where: {
            id,
        },
        data,
    });
