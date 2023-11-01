import prisma from "../config/prisma";
import { CreateClientHistoryType } from "../schemas/prisma";

export const createClientHistory = async (data: CreateClientHistoryType) =>
    await prisma.clientHistory.create({ data });
