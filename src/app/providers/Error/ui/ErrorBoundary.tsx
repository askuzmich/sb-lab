import React, { ErrorInfo, ReactNode, Suspense } from "react";
import { ErrorWidget } from "widgets/Error";

interface ErrorBoundaryProps {
  children: ReactNode;
}

interface ErrorBoundaryState {
  hasError: boolean;
}

class ErrorBoundary extends React.Component<ErrorBoundaryProps, ErrorBoundaryState> {
  constructor(props: ErrorBoundaryProps) {
    super(props);
    this.state = { hasError: false };
  }

  static getDerivedStateFromError(error: Error) {
    return { hasError: true };
  }

  componentDidCatch(error: Error, errorInfo: ErrorInfo): void {
    console.log("appLoggerService:"); // , error, errorInfo
  }

  render(): React.ReactNode {
    const { hasError } = this.state;
    const { children } = this.props;

    return hasError ? <Suspense fallback=""><ErrorWidget /></Suspense> : children;
  }
}

export default ErrorBoundary;
