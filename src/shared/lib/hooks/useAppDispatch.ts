import { TypedDispatch } from "app/providers/StoreProvider";
import { useDispatch } from "react-redux";

export const useAppDispatch = () => useDispatch<TypedDispatch>();
