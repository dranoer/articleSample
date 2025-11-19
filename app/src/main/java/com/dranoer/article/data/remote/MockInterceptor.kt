package com.dranoer.article.data.remote

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

//region Articles
private const val articlesJson = """
[
  {
    "id": "1",
    "title": "How to Reset Your Password",
    "summary": "Learn how to reset your account password safely and securely.",
    "updatedAt": "2025-08-01T10:30:00Z"
  },
  {
    "id": "2",
    "title": "Managing Notifications",
    "summary": "Control push, email, and in-app notifications.",
    "updatedAt": "2025-08-02T09:00:00Z"
  },
  {
    "id": "3",
    "title": "Deleting Your Account",
    "summary": "Understand the steps involved in deleting your account permanently.",
    "updatedAt": "2025-08-03T16:15:00Z"
  },
  {
    "id": "4",
    "title": "Updating Your Profile",
    "summary": "Learn how to update your profile photo and personal info.",
    "updatedAt": "2025-08-05T14:00:00Z"
  },
  {
    "id": "5",
    "title": "Troubleshooting Login Issues",
    "summary": "Quick fixes for common login problems.",
    "updatedAt": "2025-08-06T18:00:00Z"
  },
  {
    "id": "6",
    "title": "What is Two-Factor Authentication?",
    "summary": "Add an extra layer of security to your account.",
    "updatedAt": "2025-08-10T11:45:00Z"
  },
  {
    "id": "7",
    "title": "How to Change Your Email Address",
    "summary": "Keep your account secure by updating your email.",
    "updatedAt": "2025-08-11T14:30:00Z"
  },
  {
    "id": "8",
    "title": "Recovering a Disabled Account",
    "summary": "Steps to recover access to a disabled or locked account.",
    "updatedAt": "2025-08-12T09:20:00Z"
  },
  {
    "id": "9",
    "title": "Setting Up Notification Preferences",
    "summary": "Customize how you receive alerts from the app.",
    "updatedAt": "2025-08-14T08:40:00Z"
  },
  {
    "id": "10",
    "title": "Managing Connected Devices",
    "summary": "View and manage devices with account access.",
    "updatedAt": "2025-08-15T17:05:00Z"
  }
]
"""
//endregion

// region Details
private val DetailsJson = mapOf(
    "1" to """
{
  "id": "1",
  "title": "How to Reset Your Password",
  "contentMarkdown": "# Reset Your Password\n\nFollow these steps to reset your password:\n1. Open the login screen.\n2. Tap **Forgot password**.\n3. Enter your email.\n4. Check your inbox for a reset link.\n\n> Make sure to use a secure password!",
  "updatedAt": "2025-08-01T10:30:00Z"
}
""",
    "2" to """
{
  "id": "2",
  "title": "Managing Notifications",
  "contentMarkdown": "# Manage Notifications\n\nYou can control notification settings from the **Notifications** menu:\n- Turn off push alerts\n- Enable email reminders\n- Mute specific types\n\n> Customize what matters to you!",
  "updatedAt": "2025-08-02T09:00:00Z"
}
""",
    "3" to """
{
  "id": "3",
  "title": "Deleting Your Account",
  "contentMarkdown": "# Delete Your Account\n\n**Warning:** This will permanently delete your data.\n\nSteps:\n- Go to Settings\n- Tap **Delete account**\n- Confirm your choice\n\n> This action is irreversible.",
  "updatedAt": "2025-08-03T16:15:00Z"
}
""",
    "4" to """
{
  "id": "4",
  "title": "Updating Your Profile",
  "contentMarkdown": "# Update Your Profile\n\nWant to change your profile info?\n\nSteps:\n1. Open profile\n2. Tap edit\n3. Update your photo, name, or bio\n\n> Profile updates are visible instantly!",
  "updatedAt": "2025-08-05T14:00:00Z"
}
""",
    "5" to """
{
  "id": "5",
  "title": "Troubleshooting Login Issues",
  "contentMarkdown": "# Troubleshoot Login Issues\n\nIf you're having trouble logging in:\n- Check internet\n- Verify email/password\n- Reset password\n\n> Need more help? Contact support.",
  "updatedAt": "2025-08-06T18:00:00Z"
}
""",
    "6" to """
{
  "id": "6",
  "title": "What is Two-Factor Authentication?",
  "contentMarkdown": "# Two-Factor Authentication\n\nAdd an extra layer of security by enabling 2FA.\n\nSteps:\n- Go to Settings > Security\n- Enable 2FA\n- Save recovery codes\n\n> Protect your account from unauthorized access.",
  "updatedAt": "2025-08-10T11:45:00Z"
}
""",
    "7" to """
{
  "id": "7",
  "title": "How to Change Your Email Address",
  "contentMarkdown": "# Change Your Email\n\nYou can update your email in the Account section.\n\nSteps:\n- Go to Settings > Account\n- Tap **Change Email**\n- Verify via confirmation link\n\n> Remember, you'll need access to the new email.",
  "updatedAt": "2025-08-11T14:30:00Z"
}
""",
    "8" to """
{
  "id": "8",
  "title": "Recovering a Disabled Account",
  "contentMarkdown": "# Recover Disabled Account\n\nYour account may be disabled due to suspicious activity.\n\nTo recover:\n- Contact support\n- Verify identity\n- Follow restoration steps\n\n> Response time is typically 24–48 hours.",
  "updatedAt": "2025-08-12T09:20:00Z"
}
""",
    "9" to """
{
  "id": "9",
  "title": "Setting Up Notification Preferences",
  "contentMarkdown": "# Notification Preferences\n\nCustomize alerts depending on your needs.\n\nChoose between:\n- App notifications\n- Email summaries\n- No notifications\n\n> Your preferences save instantly.",
  "updatedAt": "2025-08-14T08:40:00Z"
}
""",
    "10" to """
{
  "id": "10",
  "title": "Managing Connected Devices",
  "contentMarkdown": "# Manage Connected Devices\n\nView and manage devices logged into your account.\n\nOptions:\n- Remove old devices\n- Force log out\n- Enable device alerts\n\n> Always keep your account secure.",
  "updatedAt": "2025-08-15T17:05:00Z"
}
"""
)
//endregion

//region Error
private const val backendErrorJson = """
{
  "errorCode": "ARTICLE_NOT_FOUND",
  "errorTitle": "Article not found",
  "errorMessage": "The article could not be found."
}
"""
//endregion

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        val (code, body) = when {
            path == "/articles" -> 200 to articlesJson

            path.startsWith("/articles/") -> {
                val id = path.substringAfterLast("/")
                val detail = DetailsJson[id]
                if (detail != null) {
                    200 to detail
                } else {
                    400 to backendErrorJson
                }
            }

            else -> 500 to backendErrorJson
        }

        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(code)
            .message("OK")
            .body(body.toByteArray().toResponseBody("application/json".toMediaType()))
            .build()
    }
}