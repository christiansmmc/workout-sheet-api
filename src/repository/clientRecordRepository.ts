import prisma from "../config/prisma";
import { CreateClientRecordType } from "../schemas/clientRecordSchema";

export const createClientHistory = async (data: CreateClientRecordType) =>
    await prisma.clientRecord.create({ data });
